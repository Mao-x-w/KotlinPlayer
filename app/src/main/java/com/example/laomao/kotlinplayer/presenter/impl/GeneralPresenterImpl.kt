package com.example.laomao.kotlinplayer.presenter.impl

import com.example.laomao.kotlinplayer.net.ResponseHandler
import com.example.laomao.kotlinplayer.presenter.`interface`.IGeneralPresenter
import com.example.laomao.kotlinplayer.view.IBaseView

/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 11-15
 *
 */
abstract class GeneralPresenterImpl<R>(var view:IBaseView):IGeneralPresenter,ResponseHandler<R> {
    override fun onError(msg: String?) {
        view.onError(msg)
    }

    override fun onSuccess(res: R?) {
        onGetDatas(res)
    }

    override fun onMoreSuccess(res: R?) {
    }

    override fun getDatas() {
        loadDatas()
    }

    abstract fun loadDatas()

    abstract fun onGetDatas(res: R?)

}