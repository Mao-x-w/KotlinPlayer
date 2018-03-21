package com.example.laomao.kotlinplayer.view

import com.example.laomao.kotlinplayer.model.Tab

/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 11-07
 *
 */
interface ITabView:IBaseView {
    fun onGetTabs(tabs:List<Tab>?)
}