package com.example.laomao.kotlinplayer.presenter.impl

import com.example.laomao.kotlinplayer.model.Home
import com.example.laomao.kotlinplayer.net.HomeRequest
import com.example.laomao.kotlinplayer.view.IBaseListView

/**
 * User: laomao
 * Date: 2018-03-05
 * Time: 10-43
 *
 */
class HomePresenterImpl(var homeView: IBaseListView<Home>) : BaseListPresenterImpl<Home>(homeView) {

    override fun loadMore() {
        HomeRequest("http://rapapi.org/mockjsdata/32041/home",this).isMore(true).execute()
    }

    override fun load() {
        HomeRequest("http://rapapi.org/mockjsdata/32041/home",this).execute()
    }

}