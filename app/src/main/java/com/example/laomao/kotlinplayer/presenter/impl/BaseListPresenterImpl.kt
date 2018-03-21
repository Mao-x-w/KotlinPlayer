package com.example.laomao.kotlinplayer.presenter.impl

import com.example.laomao.kotlinplayer.net.ResponseHandler
import com.example.laomao.kotlinplayer.presenter.`interface`.IBaseListPresenter
import com.example.laomao.kotlinplayer.view.IBaseListView

/**
 * User: laomao
 * Date: 2018-03-06
 * Time: 09-54
 *
 */
abstract class BaseListPresenterImpl<R>(var view:IBaseListView<R>?):IBaseListPresenter, ResponseHandler<R> {

    override fun onMoreSuccess(res: R?) {
        view?.onMoreSuccess(res)
    }

    override fun onError(msg: String?) {
        view?.onError(msg)
    }

    override fun onSuccess(res: R?) {
        view?.onSuccess(res)
    }

    override fun loadMoreDatas() {
        loadMore()
    }

    override fun loadDatas() {
        load()
    }

    fun destoryView(){
        view?.let {
            view=null
        }
    }

    /**
     * 加载更多
     */
    abstract fun loadMore()

    /**
     * 加载数据
     */
    abstract fun load()

}