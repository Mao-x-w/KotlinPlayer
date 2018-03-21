package com.example.laomao.kotlinplayer.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.laomao.kotlinplayer.view.IBaseView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

/**
 * User: laomao
 * Date: 2018-02-27
 * Time: 17-43
 *
 */
abstract class BaseFragment:Fragment(),AnkoLogger,IBaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }

    override fun onError(message: String?) {
    }

    /**
     * 初始化布局，必须实现
     */
    abstract fun initView(): View?

    /**
     * 初始化
     */
    open protected fun init() {

    }

    /**
     * 初始化监听器
     */
    open protected fun initListener() {

    }

    /**
     * 初始化数据
     */
    open protected fun initData() {

    }

    /**
     * toast提示
     */
    protected fun myToast(msg:String){
        context!!.runOnUiThread { toast(msg) }
    }
}