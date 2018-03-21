package com.example.laomao.kotlinplayer.ui.fragment

import android.view.View
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.adapter.MvPagerAdapter
import com.example.laomao.kotlinplayer.base.BaseFragment
import com.example.laomao.kotlinplayer.model.Tab
import com.example.laomao.kotlinplayer.presenter.impl.TabPresenterImpl
import com.example.laomao.kotlinplayer.view.ITabView
import kotlinx.android.synthetic.main.fragment_mv.*

/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 15-56
 * MV界面
 */
class MvFrament : BaseFragment(), ITabView {

    val presenter by lazy { TabPresenterImpl(this) }
    var pagerAdapter:MvPagerAdapter?=null

    override fun initView(): View? {
        val view = View.inflate(context, R.layout.fragment_mv, null)
        return view
    }

    override fun initData() {
        presenter.loadDatas()
    }

    override fun onGetTabs(tabs: List<Tab>?) {
        tablayout.setupWithViewPager(viewpager)

        pagerAdapter= MvPagerAdapter(tabs!!,childFragmentManager)
        viewpager.adapter=pagerAdapter
    }
}