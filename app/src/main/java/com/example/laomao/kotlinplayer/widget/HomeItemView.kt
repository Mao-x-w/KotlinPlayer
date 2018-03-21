package com.example.laomao.kotlinplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.model.Music
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_item_view.view.*

/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 18-03
 *
 */
class HomeItemView:RelativeLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 所有构造方法都会走这个
     */
    init {
        View.inflate(context, R.layout.home_item_view,this)
    }

    fun setData(music: Music) {
        music_name.setText(music.title)
        music_desc.setText(music.desc)
        Picasso.with(context).load(music.image).into(music_image)
    }
}