package com.example.laomao.kotlinplayer.net

import com.example.laomao.kotlinplayer.model.TabObj
import com.example.laomao.kotlinplayer.utils.StringUtil

/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 11-03
 *
 */
class TabRequest(handler: ResponseHandler<TabObj>): BaseRequest<TabObj>("http://rapapi.org/mockjsdata/32041/tab",handler) {
    override fun getLocalResult(): String {
        return StringUtil.testTabResult
    }
}