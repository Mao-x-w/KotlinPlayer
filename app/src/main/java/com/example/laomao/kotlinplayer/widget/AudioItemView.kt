package com.example.laomao.kotlinplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.model.Audio
import kotlinx.android.synthetic.main.item_audio.view.*

/**
 * User: laomao
 * Date: 2018-03-11
 * Time: 17-25
 *
 */
class AudioItemView:RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_audio,this)
    }

    fun setData(audio: Audio) {
        artist.setText(audio.artist)
        displayName.setText(audio.displayName)
        size.setText(audio.size)
    }
}