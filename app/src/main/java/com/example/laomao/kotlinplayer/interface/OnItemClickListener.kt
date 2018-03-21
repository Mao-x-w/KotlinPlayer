package com.example.laomao.kotlinplayer.`interface`

/**
 * User: laomao
 * Date: 2018-03-17
 * Time: 02-15
 *
 */
interface OnItemClickListener<RES> {
    fun onItemClick(list: List<RES>,position:Int)
}