package com.example.laomao.kotlinplayer.view

/**
 * User: laomao
 * Date: 2018-03-06
 * Time: 09-51
 *
 * 列表界面的baseview
 */
interface IBaseListView<R>:IBaseView {
    fun onSuccess(r: R?)
    fun onMoreSuccess(home: R?)
}