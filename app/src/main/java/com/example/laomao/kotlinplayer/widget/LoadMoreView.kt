package com.example.laomao.kotlinplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.laomao.kotlinplayer.R

/**
 * User: laomao
 * Date: 2018-03-02
 * Time: 18-19
 *
 */
class LoadMoreView :RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.load_more_view,this)
    }
}