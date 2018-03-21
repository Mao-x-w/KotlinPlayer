package com.example.laomao.kotlinplayer.ui.activity

import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * User: laomao
 * Date: 2018-02-27
 * Time: 17-53
 *
 */
class SplashActivity: BaseActivity(), ViewPropertyAnimatorListener {
    override fun onAnimationEnd(view: View?) {
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(view: View?) {
    }

    override fun onAnimationStart(view: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(splash_imageview).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000).start()
    }
}