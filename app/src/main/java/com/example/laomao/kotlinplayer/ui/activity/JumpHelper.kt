package com.example.laomao.kotlinplayer.ui.activity

import android.content.Context
import org.jetbrains.anko.startActivity

/**
 * User: laomao
 * Date: 2018-02-28
 * Time: 10-37
 *
 */
object JumpHelper {

    fun jumpMain(context:Context){
        context.startActivity<MainActivity>()
    }
}