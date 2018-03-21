package com.example.laomao.kotlinplayer.ui.fragment

import android.content.AsyncQueryHandler
import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.adapter.AudioAdapter
import com.example.laomao.kotlinplayer.base.BaseFragment
import com.example.laomao.kotlinplayer.ui.activity.AudioAcitivity
import com.example.laomao.kotlinplayer.utils.CursorUtils
import com.yanzhenjie.permission.Action
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import kotlinx.android.synthetic.main.fragment_v_rank.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 15-56
 * 音乐排行榜界面
 */
class VRankFrament : BaseFragment() {

    val adapter by lazy { AudioAdapter() }

    override fun initView(): View? {
        val view = View.inflate(context, R.layout.fragment_v_rank, null)
        return view
    }

    override fun initListener() {
        recyclerView.layoutManager=LinearLayoutManager(context)
        adapter.isShowMore=false
        recyclerView.adapter=adapter
        adapter.setOnItemClickListener { list, position ->
            startActivity<AudioAcitivity>("list" to list,"position" to position)
        }
    }

    override fun initData() {
        // 获取权限
        AndPermission.with(this)
                .permission(Permission.READ_EXTERNAL_STORAGE)
                .onGranted(object : Action {
                    override fun onAction(permissions: List<String>) {
                        getAudioDatas()
                    }
                })
                .onDenied(object : Action {
                    override fun onAction(permissions: List<String>) {

                    }
                }).start()
    }

    /**
     * 获取音频数据
     */
    fun getAudioDatas() {
        val resolver = context!!.contentResolver
        AudioHandler(resolver).startQuery(0,adapter,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                , arrayOf(MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST), null, null,null)
    }

    /**
     * 获取音频数据成功回调
     */
    class AudioHandler(resolver: ContentResolver) : AsyncQueryHandler(resolver) {
        override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
            val audioList = CursorUtils.getAudioList(cursor)
            val audioAdapter = cookie as AudioAdapter
            audioAdapter.setDatas(audioList)
        }
    }

}