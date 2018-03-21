package com.example.laomao.kotlinplayer.ui.fragment

import com.example.laomao.kotlinplayer.adapter.BaseListAdapter
import com.example.laomao.kotlinplayer.adapter.HomeAdapter
import com.example.laomao.kotlinplayer.base.BaseListFragment
import com.example.laomao.kotlinplayer.model.Home
import com.example.laomao.kotlinplayer.model.Music
import com.example.laomao.kotlinplayer.presenter.impl.BaseListPresenterImpl
import com.example.laomao.kotlinplayer.presenter.impl.HomePresenterImpl
import com.example.laomao.kotlinplayer.ui.activity.MvPlayerActivity
import org.jetbrains.anko.support.v4.startActivity

/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 13-48
 * Mv界面中的PagerAdapter中需要的Fragment
 */
class MvPagerFragment: BaseListFragment<Home, Music>() {

    val homeAdapter by lazy { HomeAdapter() }

    override fun getList(r: Home?): List<Music>? {
        return r?.datas
    }

    override fun getSpecialAdapter(): BaseListAdapter<Music> {
        return homeAdapter
    }

    override fun getSpecialPresenter(): BaseListPresenterImpl<Home> {
        return HomePresenterImpl(this)
    }

    override fun initListener() {
        super.initListener()
        homeAdapter?.setOnItemClickListener { list, position ->
            startActivity<MvPlayerActivity>("url" to list.get(position).url)
        }
    }

}