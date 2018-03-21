package com.example.laomao.kotlinplayer.ui.activity

import android.support.v7.widget.Toolbar
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.base.BaseActivity
import com.example.laomao.kotlinplayer.utils.FragmentUtil
import com.example.laomao.kotlinplayer.utils.ToolbarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(),ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initListener() {
        main_bottombar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container,FragmentUtil.instance.getFragmentById(it),it.toString())
            transaction.commit()
        }
    }

    override fun initData() {
        initMainToolbar()
    }

}
