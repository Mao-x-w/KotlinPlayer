package com.example.laomao.kotlinplayer.adapter

import android.content.Context
import android.view.View
import com.example.laomao.kotlinplayer.model.Audio
import com.example.laomao.kotlinplayer.widget.AudioItemView

/**
 * User: laomao
 * Date: 2018-03-11
 * Time: 17-22
 * 音频数据对应的Adapter
 */
class AudioAdapter: BaseListAdapter<Audio>() {
    override fun getItemView(context: Context?): View {
        return AudioItemView(context)
    }

    override fun setItemData(holder: BaseViewHolder?, datas: ArrayList<Audio>, position: Int) {
        val audioItemView = holder?.itemView as AudioItemView
        audioItemView.setData(datas.get(position))
    }
}