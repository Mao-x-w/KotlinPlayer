package com.example.laomao.kotlinplayer.utils

import com.example.laomao.kotlinplayer.model.Lyric
import java.io.File

/**
 * User: laomao
 * Date: 2018-03-19
 * Time: 17-25
 *
 */
object LyricUtil {

    /**
     * 解析歌词
     */
    public fun parseLyric(file:File):List<Lyric>{
        var list=ArrayList<Lyric>()
        if (!file.exists()){
            list.add(Lyric(0,"没有找到该歌曲的歌词"))
        }else{
            val lines = file.readLines()
            for (line in lines) {
                list.addAll(parseLine(line))
            }
        }
        return list
    }

    /**
     * 解析每一行歌词到一个bean中
     */
    private fun parseLine(line: String): List<Lyric> {
        var list=ArrayList<Lyric>()
        val splits = line.split("]")
        val content = splits.get(splits.size - 1)
        for (index in 0 until splits.size-1){
            list.add(Lyric(parseTime(splits.get(index)),content))
        }
        return list
    }

    /**
     * 解析歌词中的时间
     */
    private fun parseTime(get: String): Int {
        var hour=0
        var min=0
        var sec=0

        var time=get.substring(1)
        val split = time.split(":")
        if (split.size==3){
            hour=split.get(0).toInt()*60*60*1000
            min=split.get(1).toInt()*60*1000
            hour=split.get(2).toInt()*1000
        }else if (split.size==2){
            min=split.get(0).toInt()*60*1000
            hour=split.get(1).toInt()*1000
        }
        return hour+min+sec
    }

}