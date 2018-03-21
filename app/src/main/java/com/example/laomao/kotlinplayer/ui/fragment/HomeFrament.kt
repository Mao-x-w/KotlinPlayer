package com.example.laomao.kotlinplayer.ui.fragment

import com.example.laomao.kotlinplayer.adapter.BaseListAdapter
import com.example.laomao.kotlinplayer.adapter.HomeAdapter
import com.example.laomao.kotlinplayer.base.BaseListFragment
import com.example.laomao.kotlinplayer.model.Home
import com.example.laomao.kotlinplayer.model.Music
import com.example.laomao.kotlinplayer.presenter.impl.BaseListPresenterImpl
import com.example.laomao.kotlinplayer.presenter.impl.HomePresenterImpl

/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 15-56
 * 首页
 */
class HomeFrament : BaseListFragment<Home, Music>() {
    override fun getList(r: Home?): List<Music>? {
        return r?.datas
    }

    override fun getSpecialAdapter(): BaseListAdapter<Music> {
        return HomeAdapter()
    }

    override fun getSpecialPresenter(): BaseListPresenterImpl<Home> {
        return HomePresenterImpl(this)
    }


}