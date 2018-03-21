package com.example.laomao.kotlinplayer.model

import android.os.Parcel
import android.os.Parcelable

/**
 * User: laomao
 * Date: 2018-03-11
 * Time: 17-06
 *
 */
class Audio(var data:String,var size:String,var displayName:String,var artist:String) :Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
        parcel.writeString(size)
        parcel.writeString(displayName)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Audio> {
        override fun createFromParcel(parcel: Parcel): Audio {
            return Audio(parcel)
        }

        override fun newArray(size: Int): Array<Audio?> {
            return arrayOfNulls(size)
        }
    }
}