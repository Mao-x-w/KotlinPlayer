package com.example.laomao.kotlinplayer.utils

import android.os.Handler
import android.os.Looper

/**
 * User: laomao
 * Date: 2018-03-02
 * Time: 15-53
 *
 */
object ThreadUtils {
    val handler= Handler(Looper.getMainLooper())

    fun runOnMainThread(runnable: Runnable){
        handler.post(runnable)
    }
}