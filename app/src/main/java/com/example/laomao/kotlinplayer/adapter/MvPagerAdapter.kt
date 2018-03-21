package com.example.laomao.kotlinplayer.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.laomao.kotlinplayer.model.Tab
import com.example.laomao.kotlinplayer.ui.fragment.MvPagerFragment

/**
 * User: laomao
 * Date: 2018-03-07
 * Time: 11-56
 * Mv界面对应的PagerAdapter
 */
class MvPagerAdapter(val list:List<Tab>,manager:FragmentManager):FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        // 给fragment传递参数
        var fragment= MvPagerFragment()
        var bundle=Bundle()
        bundle.putString("id",list.get(position).id)
        fragment.arguments=bundle
        return fragment
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return list.get(position).name
    }
}