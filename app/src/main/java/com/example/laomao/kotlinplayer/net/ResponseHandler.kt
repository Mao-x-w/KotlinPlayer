package com.example.laomao.kotlinplayer.net

/**
 * User: laomao
 * Date: 2018-03-05
 * Time: 13-57
 *
 */
interface ResponseHandler<R> {
    /**
     * 加载数据失败
     */
    fun onError(msg: String?)
    /**
     * 加载数据成功
     */
    fun onSuccess(res: R?)

    /**
     * 加载更多数据成功
     */
    fun onMoreSuccess(res: R?)
}