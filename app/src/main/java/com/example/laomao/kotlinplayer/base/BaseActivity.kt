package com.example.laomao.kotlinplayer.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * User: laomao
 * Date: 2018/2/27
 * Time: 下午5:17
 */
abstract class BaseActivity : AppCompatActivity(),AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
        initListener()
        initData()
    }

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
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * toast提示
     */
    protected fun myToast(msg:String){
        runOnUiThread { toast(msg) }
    }

    /**
     * 启动一个新的Activity并finish掉当前的
     */
    protected inline fun <reified T:BaseActivity> startActivityAndFinish(){
        startActivity<T>()
        finish()
    }
}