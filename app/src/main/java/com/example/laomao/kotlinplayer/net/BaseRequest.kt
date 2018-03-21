package com.example.laomao.kotlinplayer.net

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

/**
 * User: laomao
 * Date: 2018-03-05
 * Time: 13-56
 * 仿照Volley加载数据方式，封装的加载接口基类
 */
abstract class BaseRequest<R>(var url:String,var handler:ResponseHandler<R>) {

    var isMore:Boolean=false

    /**
     * 解析网络返回数据
     */
    fun parseResult(result: String?): R {
        var realResult=""
        if (result==null){
            realResult=getLocalResult()
        }else{
            realResult=result
        }
        val gson = Gson()
        var type = (javaClass.getGenericSuperclass() as ParameterizedType).getActualTypeArguments()[0]
        return gson.fromJson<R>(realResult,type)
    }

    /**
     * 执行网络请求
     */
    fun execute() {
        NetManager.instance.sendRequest(this)
    }

    /**
     * 判定是否是加载更多
     */
    fun isMore(b: Boolean): BaseRequest<R> {
        this.isMore=b
        return this
    }

    /**
     * 数据加载失败时加载本地数据
     */
    abstract fun getLocalResult():String

}