package com.example.laomao.kotlinplayer.utils

import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.example.laomao.kotlinplayer.model.Audio

/**
 * User: laomao
 * Date: 2018-03-10
 * Time: 21-36
 *
 */
object CursorUtils {

    fun printCursor(cursor:Cursor?){
        cursor?.let {
            cursor.moveToPosition(-1)
            while (cursor.moveToNext()){
                for (index in 0 until cursor.columnCount){
                    Log.e("Cursor","key=${cursor.getColumnName(index)}   value=${cursor.getString(index)}")
                }
            }
        }
    }

    fun getAudioList(cursor: Cursor?):List<Audio>{
        var list=ArrayList<Audio>()
        cursor?.let {
            cursor.moveToPosition(-1)
            while (cursor.moveToNext()){
                var displayName=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))
                var audio=Audio(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)),
                        displayName.substring(0,displayName.lastIndexOf(".")),
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)))
                list.add(audio)
            }
            cursor.close()
        }
        return list
    }
}