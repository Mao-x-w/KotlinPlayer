package com.example.laomao.kotlinplayer.net

import com.example.laomao.kotlinplayer.utils.ThreadUtils
import okhttp3.*
import java.io.IOException

/**
 * User: laomao
 * Date: 2018-03-05
 * Time: 13-59
 *
 */
class NetManager private constructor(){

    val client by lazy { OkHttpClient() }

    companion object {
        val instance by lazy { NetManager() }
    }

    /**
     * 发送网络请求
     */
    fun <R>sendRequest(req:BaseRequest<R>){
        val request = Request.Builder().url(req.url).build()
        client.newCall(request).enqueue(object : Callback {
            /**
             * 加载数据失败，为保证App展示，返回本地数据
             */
            override fun onFailure(call: Call?, e: IOException?) {
                ThreadUtils.runOnMainThread(Runnable {
//                    req.handler.onError(e?.message)
                    // 为保证有数据，返回本地数据
                    var parseResult: R =req.parseResult(null)
                    if (req.isMore){
                        req.handler.onMoreSuccess(parseResult)
                    }else{
                        req.handler.onSuccess(parseResult)
                    }
                })
            }

            /**
             * 加载数据成功返回数据
             */
            override fun onResponse(call: Call?, response: Response?) {
                val result = response?.body()?.string()
                println(result)
                var parseResult: R =req.parseResult(result)
                ThreadUtils.runOnMainThread(Runnable {
                    if (req.isMore){
                        req.handler.onMoreSuccess(parseResult)
                    }else{
                        req.handler.onSuccess(parseResult)
                    }
                })
            }

        })
    }
}