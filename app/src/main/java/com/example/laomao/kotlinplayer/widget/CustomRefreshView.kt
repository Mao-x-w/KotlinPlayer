package com.example.laomao.kotlinplayer.widget

import android.content.Context
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet

/**
 * User: laomao
 * Date: 2018-03-02
 * Time: 16-34
 *
 */
class CustomRefreshView: SwipeRefreshLayout {

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)

    init {
        setColorSchemeColors(Color.RED,Color.YELLOW,Color.GREEN)
    }
}