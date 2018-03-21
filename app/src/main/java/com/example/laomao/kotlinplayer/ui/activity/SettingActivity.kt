package com.example.laomao.kotlinplayer.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.base.BaseActivity
import com.example.laomao.kotlinplayer.utils.ToolbarManager
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

/**
 * User: laomao
 * Date: 2018-02-28
 * Time: 18-04
 *
 */
class SettingActivity: BaseActivity(),ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolbar()
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val boolean = sp.getBoolean(resources.getString(R.string.key_push),false)
        toast("$boolean")
    }
}