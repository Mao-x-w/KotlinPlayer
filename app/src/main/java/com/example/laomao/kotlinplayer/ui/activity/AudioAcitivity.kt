package com.example.laomao.kotlinplayer.ui.activity

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.view.View
import android.widget.SeekBar
import android.widget.VideoView
import com.bumptech.glide.Glide
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.`interface`.OnItemClickListener
import com.example.laomao.kotlinplayer.base.BaseActivity
import com.example.laomao.kotlinplayer.model.Audio
import com.example.laomao.kotlinplayer.service.AudioService
import com.example.laomao.kotlinplayer.service.IAudioService
import com.example.laomao.kotlinplayer.utils.BusTag
import com.example.laomao.kotlinplayer.utils.StringUtil
import com.example.laomao.kotlinplayer.widget.AudioBottomDialog
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.hwangjr.rxbus.thread.EventThread
import kotlinx.android.synthetic.main.activity_audio.*

/**
 * User: laomao
 * Date: 2018-03-11
 * Time: 21-46
 * 播放音频界面
 */
class AudioAcitivity: BaseActivity(), View.OnClickListener {

    val connection by lazy { AudioServiceConnection() }
    var audioService:IAudioService?=null
    var mMaxProgress:Int=0
    private val MSG_PROGRESS: Int=0

    override fun getLayoutId(): Int {
        return R.layout.activity_audio
    }

    override fun init() {
        RxBus.get().register(this)
        Glide.with(this).load(R.mipmap.audio_bg).into(audioBg)
        VideoView(this).start()
    }

    override fun initListener() {
        back.setOnClickListener { finish() }
        start.setOnClickListener(this)
        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    audioService?.changeProgress(progress)
                    updateProgress(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        lyricView.setLiricMoveListener {
            audioService?.changeProgress(it)
            updateProgress(it)
        }
        mode.setOnClickListener(this)
        next.setOnClickListener(this)
        before.setOnClickListener(this)
        audioList.setOnClickListener(this)
    }

    override fun initData() {
        intent.setClass(this, AudioService::class.java)
        startService(intent)
        bindService(intent,connection,BIND_AUTO_CREATE)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.start->{
                changePlayState()
                changeButtonState()
            }
            R.id.mode->{
                // 改变播放模式
                audioService?.changeMode()
                // 改变按钮状态
                changeModeButtonState()
            }
            R.id.next->{
                audioService?.playNext()
            }
            R.id.before->{
                audioService?.playPrevious()
            }
            R.id.audioList->{
                AudioBottomDialog(this,audioService?.getDataList(),object : OnItemClickListener<Audio> {
                    override fun onItemClick(list: List<Audio>, position: Int) {
                        audioService?.playPosition(position)
                    }

                }).show()
            }
        }
    }

    /**
     * 修改播放模式状态按钮
     */
    private fun changeModeButtonState() {
        when(audioService?.getPlayMode()){
            AudioService.MODE_ALL->mode.setImageResource(R.mipmap.mode_all)
            AudioService.MODE_SINGLE->mode.setImageResource(R.mipmap.mode_single)
            AudioService.MODE_RANDOM->mode.setImageResource(R.mipmap.mode_random)
        }
    }

    /**
     * 修改开始，暂停按钮状态
     */
    private fun changeButtonState() {
        audioService?.let {
            if (audioService!!.isCurrentPlaying()){
                start.setImageResource(R.mipmap.start)
                handler.sendEmptyMessage(MSG_PROGRESS)
            }else{
                start.setImageResource(R.mipmap.pause)
                handler.removeMessages(MSG_PROGRESS)
            }
        }

    }

    /**
     * 修改当前播放状态
     */
    private fun changePlayState() {
        audioService?.changePlayState()
    }

    @Subscribe(tags = arrayOf(Tag(BusTag.VIDEO_DATA)),thread = EventThread.MAIN_THREAD)
    fun onGetBusData(audio:Audio) {
        displayName.setText(audio.displayName)
        artist.setText(audio.artist)

        lyricView.loadLyric(audio.displayName)

        // 设置播放按钮状态
        changeButtonState()

        // 设置播放进度状态
        mMaxProgress =audioService?.getDuration()?:0
        seekBar.max= mMaxProgress
        changeProgress()
        changeModeButtonState()
    }

    /**
     * 用于定时更新播放进度的handler
     */
    var handler=object :Handler(){
        override fun handleMessage(msg: Message?) {
            when(msg?.what){
                MSG_PROGRESS->changeProgress()
            }
        }
    }

    /**
     * 更新播放进度
     */
    private fun changeProgress() {
        var progress=audioService?.getProgress()?:0
        updateProgress(progress)
        handler.sendEmptyMessageDelayed(MSG_PROGRESS,100)
    }

    /**
     * 更改播放进度
     */
    private fun updateProgress(pro: Int) {
        seekBar.progress = pro
        tvProgress.text = StringUtil.formatTime(pro)+"/"+ StringUtil.formatTime(mMaxProgress)
        lyricView.setAudioDuration(mMaxProgress)
        lyricView.updateProgress(pro)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        RxBus.get().unregister(this)
        handler.removeCallbacksAndMessages(null)
    }

    inner class AudioServiceConnection : ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            audioService = service as IAudioService
        }

    }

}