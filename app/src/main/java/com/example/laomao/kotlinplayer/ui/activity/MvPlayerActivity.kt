package com.example.laomao.kotlinplayer.ui.activity

import android.media.MediaPlayer
import cn.jzvd.JZVideoPlayer
import cn.jzvd.JZVideoPlayerStandard
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mv_player.*


/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 17-53
 *
 */
class MvPlayerActivity : BaseActivity() {

    var player: MediaPlayer? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_mv_player
    }

    override fun initData() {
        var data=intent.data
        println("path:"+data)
        if (data==null){
            videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛")
        }else{
            videoplayer.setUp(data.toString(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, data.path.toString())
        }
    }

    override fun onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
    }

}