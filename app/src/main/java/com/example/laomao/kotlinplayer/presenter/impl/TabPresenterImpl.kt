package com.example.laomao.kotlinplayer.presenter.impl

import com.example.laomao.kotlinplayer.model.TabObj
import com.example.laomao.kotlinplayer.net.TabRequest
import com.example.laomao.kotlinplayer.view.ITabView

/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 11-13
 *
 */
class TabPresenterImpl(var tabView:ITabView): GeneralPresenterImpl<TabObj>(tabView) {
    override fun loadDatas() {
        TabRequest(this).execute()
    }

    override fun onGetDatas(res: TabObj?) {
        tabView.onGetTabs(res?.tab)
    }
}