package com.example.laomao.kotlinplayer.utils

import android.os.Environment
import java.io.File

/**
 * User: laomao
 * Date: 2018-03-19
 * Time: 17-54
 *
 */
object LyricLoader {

    var dir=File(Environment.getExternalStorageDirectory(),"Download/Lyric")

    public fun getLyricFile(lyricName:String):File{
        return File(dir,lyricName+".lrc")
    }
}