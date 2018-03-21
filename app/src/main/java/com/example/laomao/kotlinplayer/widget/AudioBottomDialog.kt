package com.example.laomao.kotlinplayer.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.`interface`.OnItemClickListener
import com.example.laomao.kotlinplayer.adapter.AudioAdapter
import com.example.laomao.kotlinplayer.model.Audio

/**
 * User: laomao
 * Date: 2018-03-16
 * Time: 18-13
 *
 */
class AudioBottomDialog(context: Context, datas: List<Audio>?, listener: OnItemClickListener<Audio>) : BottomSheetDialog(context) {

    val audioAdapter by lazy { AudioAdapter() }

    init {
        val view = View.inflate(context, R.layout.audio_item, null);
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = audioAdapter
        audioAdapter.isShowMore = false
        audioAdapter.setDatas(datas)
        audioAdapter.setOnItemClickListener { list, position ->
            listener.onItemClick(list, position)
            dismiss()
        }
        setContentView(view)
        setPeekHeight(600)
    }
}