package com.example.laomao.kotlinplayer.utils

import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.base.BaseFragment
import com.example.laomao.kotlinplayer.ui.fragment.HomeFrament
import com.example.laomao.kotlinplayer.ui.fragment.MvFrament
import com.example.laomao.kotlinplayer.ui.fragment.VRankFrament

/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 14-58
 *
 * 这里使用的是懒汉式单例模式
 */
class FragmentUtil private constructor(){

    val homeFragment :HomeFrament by lazy { HomeFrament() }
    val mvFragment :MvFrament by lazy { MvFrament() }
    val vRankFragment :VRankFrament by lazy { VRankFrament() }
//    val mineFragment :MineFrament by lazy { MineFrament() }

    companion object {
        val instance : FragmentUtil by lazy { FragmentUtil() }
    }

    /**
     * 根据tabID获取对应的Fragment
     */
    fun getFragmentById(tabId:Int):BaseFragment?{
        when(tabId){
            R.id.tab_home->return homeFragment
            R.id.tab_mv->return mvFragment
            R.id.tab_v_rank->return vRankFragment
//            R.id.tab_mine->return mineFragment
        }
        return null
    }
}