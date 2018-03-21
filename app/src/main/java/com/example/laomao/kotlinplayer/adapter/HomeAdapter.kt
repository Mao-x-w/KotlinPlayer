package com.example.laomao.kotlinplayer.adapter

import android.content.Context
import android.view.View
import com.example.laomao.kotlinplayer.model.Music
import com.example.laomao.kotlinplayer.widget.HomeItemView

/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 18-12
 *
 */
class HomeAdapter : BaseListAdapter<Music>() {

    override fun getItemView(context: Context?): View {
        return HomeItemView(context)
    }

    override fun setItemData(holder: BaseViewHolder?, datas: ArrayList<Music>, position: Int) {
        val homeItemView = holder?.itemView as HomeItemView
        homeItemView.setData(datas.get(position))
    }

}