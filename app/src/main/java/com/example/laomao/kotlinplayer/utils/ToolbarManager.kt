package com.example.laomao.kotlinplayer.utils

import android.support.v7.widget.Toolbar
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.ui.activity.SettingActivity
import org.jetbrains.anko.startActivity

/**
 * User: laomao
 * Date: 2018-02-28
 * Time: 17-44
 *
 */
interface ToolbarManager {
    val toolbar:Toolbar

    fun initMainToolbar(){
        toolbar.setTitle("kotlin影音")
        toolbar.inflateMenu(R.menu.main)

        /**
         * 如果java接口中只有一个未实现的方法 可以省略接口对象 直接用{}表示未实现的方法
         * 里面默认的参数是it  也可以自己定义参数名称  item->
         *
         */
        toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.setting->{
                    toolbar.context.startActivity<SettingActivity>()
                }
            }
            true
        }

//        toolbar.setOnMenuItemClickListener { item->
//            when(item.itemId){
//                R.id.setting->{
//                    toolbar.context.startActivity<SettingActivity>()
//                }
//            }
//            true
//        }

        /**
         * object用于申明匿名内部类
         */
//        toolbar.setOnMenuItemClickListener(object:Toolbar.OnMenuItemClickListener{
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                when(item?.itemId){
//                    R.id.setting->{
//                        toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
//                    }
//                }
//                return true
//            }
//
//        })
    }

    fun initSettingToolbar(){
        toolbar.setTitle("设置界面")
    }
}