package com.example.laomao.kotlinplayer.net

import com.example.laomao.kotlinplayer.model.Home
import com.example.laomao.kotlinplayer.utils.StringUtil

/**
 * User: laomao
 * Date: 2018-03-05
 * Time: 16-42
 *
 */
class HomeRequest(url:String,handler: ResponseHandler<Home>): BaseRequest<Home>(url,handler) {

    override fun getLocalResult(): String {
        return StringUtil.testHomeResult
    }
}