package com.example.laomao.kotlinplayer.service

import com.example.laomao.kotlinplayer.model.Audio

/**
 * User: laomao
 * Date: 2018-03-12
 * Time: 11-49
 *
 */
interface IAudioService {
    fun startPlay()
    fun changePlayState()
    fun isCurrentPlaying():Boolean
    fun getDuration(): Int
    fun getProgress(): Int
    fun changeProgress(progress: Int)
    fun changeMode()
    fun getPlayMode():Int
    fun playNext()
    fun playPrevious()
    fun getDataList(): List<Audio>?
    fun playPosition(position: Int)
}