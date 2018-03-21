package com.example.laomao.kotlinplayer.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.model.Audio
import com.example.laomao.kotlinplayer.ui.activity.AudioAcitivity
import com.example.laomao.kotlinplayer.ui.activity.MainActivity
import com.example.laomao.kotlinplayer.utils.BusTag
import com.hwangjr.rxbus.RxBus
import java.util.*

/**
 * User: laomao
 * Date: 2018-03-12
 * Time: 11-46
 *
 */
class AudioService : Service() {

    var dataList: ArrayList<Audio>? = null
    var position: Int = -1
    var player: MediaPlayer? = null
    val binder by lazy { MyBinder() }

    val FROM="from"
    val FROM_PREVIOUS=1
    val FROM_START=2
    val FROM_NEXT=3
    val FROM_CONTENT=4


    val sp by lazy { getSharedPreferences("config", Context.MODE_PRIVATE) }

    /**
     * 伴生对象，类似java中static，其他类可以通过AudioService.MODE_ALL拿到模式
     */
    companion object {
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }

    var mode = MODE_ALL

    override fun onCreate() {
        super.onCreate()
        mode=sp.getInt("mode", MODE_ALL)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val from = intent?.getIntExtra(FROM, -1)
        when(from){
            FROM_CONTENT->{binder.updateUi()}
            FROM_PREVIOUS->{binder.playPrevious()}
            FROM_START->{
                binder.changePlayState()
                binder.updateUi()
            }
            FROM_NEXT->{binder.playNext()}
            else->{
                dataList = intent?.getParcelableArrayListExtra<Audio>("list")
                position = intent?.getIntExtra("position", -1) ?: -1
                binder.startPlay()
            }
        }



        // START_STICKY 杀死service会尝试重新启动，不会传递原来intent，可能导致空指针
        // START_NOT_STICKY 杀死service不会尝试重新启动
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class MyBinder : Binder(), IAudioService {

        var manager:NotificationManager?=null
        var notify:Notification?=null

        override fun playPosition(position: Int) {
            this@AudioService.position=position
            startPlay()
        }

        override fun getDuration(): Int {
            return player?.duration ?: 0
        }

        override fun getProgress(): Int {
            return player?.currentPosition ?: 0
        }

        override fun isCurrentPlaying(): Boolean {
            return player?.isPlaying ?: false
        }

        override fun changePlayState() {
            if (isCurrentPlaying()) {
                player?.pause()
                notify?.contentView?.setImageViewResource(R.id.start,R.mipmap.pause)
                manager?.notify(1,notify)
            } else {
                player?.start()
                notify?.contentView?.setImageViewResource(R.id.start,R.mipmap.start)
                manager?.notify(1,notify)
            }
        }

        override fun startPlay() {
            if (player!=null){
                player?.reset()
                player?.release()
                player==null
            }

            player = MediaPlayer()
            player?.let { p ->
                p.setDataSource(dataList?.get(position)?.data)
                p.prepareAsync()
                p.setOnPreparedListener {
                    p.start()

                    // 开始播放后，将播放数据发出去，以供activity中界面使用
                    updateUi()

                    // 创建NotifyCation
                    showNotification()
                }
                p.setOnCompletionListener {
                    autoPlayNext()
                }
            }

        }

        private fun showNotification() {
            manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notify=getNotification()
            manager?.notify(1,notify)
        }

        private fun getNotification(): Notification? {
            val notification = NotificationCompat.Builder(this@AudioService)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setCustomContentView(getRemoteView()) // 自定义布局
                    .setContentIntent(getPendingIntent())  // 主体的点击事件
                    .setOngoing(false) // 当前的notification是否可以滑动移除
                    .build()
            return notification
        }

        private fun getRemoteView(): RemoteViews? {
            var remoteView=RemoteViews(packageName, R.layout.audio_notification)
            remoteView.setTextViewText(R.id.title,dataList?.get(position)?.displayName)
            remoteView.setImageViewResource(R.id.start,R.mipmap.start)
            remoteView.setOnClickPendingIntent(R.id.before,getPreviousIntent())
            remoteView.setOnClickPendingIntent(R.id.start,getStartIntent())
            remoteView.setOnClickPendingIntent(R.id.next,getNextIntent())
            return remoteView
        }

        private fun getPendingIntent(): PendingIntent? {
            var intentA=Intent(this@AudioService,MainActivity::class.java)
            var intentB=Intent(this@AudioService,AudioAcitivity::class.java)
            intentB.putExtra(FROM,FROM_CONTENT)
            val intents = arrayOf(intentA,intentB)
            val pendingIntent = PendingIntent.getActivities(this@AudioService, 1, intents, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        private fun getNextIntent(): PendingIntent? {
            var intent=Intent(this@AudioService,AudioService::class.java)
            intent.putExtra(FROM,FROM_NEXT)
            val pendingIntent = PendingIntent.getService(this@AudioService, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        private fun getStartIntent(): PendingIntent? {
            var intent=Intent(this@AudioService,AudioService::class.java)
            intent.putExtra(FROM,FROM_START)
            val pendingIntent = PendingIntent.getService(this@AudioService, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        private fun getPreviousIntent(): PendingIntent? {
            var intent=Intent(this@AudioService,AudioService::class.java)
            intent.putExtra(FROM,FROM_PREVIOUS)
            val pendingIntent = PendingIntent.getService(this@AudioService, 4, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        public fun updateUi() {
            RxBus.get().post(BusTag.VIDEO_DATA, dataList?.get(position))
        }

        /**
         * 自动播放下一曲
         */
        private fun autoPlayNext() {
            dataList?.let {
                when (mode) {
                    MODE_RANDOM -> {
                        // 将position随机指向一个数值
                        position = Random().nextInt(it.size)
                    }
                    MODE_ALL -> {
                        // 将position指向下一个，如果到最大那么回到0
                        if (position == ((it.size) - 1)) {
                            position = 0
                        } else {
                            position++
                        }
                    }
                    else -> {
                    }
                }
            }
            startPlay()
        }

        /**
         * 改变音乐循环模式
         * 全部-单曲
         * 单曲-随机
         * 随机-全部
         */
        override fun changeMode() {
            when(mode){
                MODE_ALL->mode=MODE_SINGLE
                MODE_SINGLE->mode=MODE_RANDOM
                MODE_RANDOM->mode=MODE_ALL
            }
            sp.edit().putInt("mode",mode).commit()
        }

        /**
         * 获取当前播放模式
         */
        override fun getPlayMode(): Int {
            return mode
        }

        override fun changeProgress(progress: Int) {
            player?.seekTo(progress)
        }

        /**
         * 播放下一曲
         */
        override fun playNext() {
            dataList?.let {
                when (mode) {
                    MODE_RANDOM -> {
                        // 将position随机指向一个数值
                        position = Random().nextInt(it.size)
                    }
                    else -> {
                        // 将position指向下一个，如果到最大那么回到0
                        if (position == ((it.size) - 1)) {
                            position = 0
                        } else {
                            position++
                        }
                    }
                }
            }
            startPlay()
        }

        /**
         * 播放上一曲
         */
        override fun playPrevious() {
            dataList?.let {
                when (mode) {
                    MODE_RANDOM -> {
                        // 将position随机指向一个数值
                        position = Random().nextInt(it.size)
                    }
                    else -> {
                        // 将position指向下一个，如果到最大那么回到0
                        if (position == 0) {
                            position = it.size-1
                        } else {
                            position--
                        }
                    }
                }
            }
            startPlay()
        }

        override fun getDataList(): List<Audio>? {
            return dataList
        }

    }
}