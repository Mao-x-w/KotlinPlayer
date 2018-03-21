package com.example.laomao.kotlinplayer.ui.fragment

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.laomao.kotlinplayer.R
import com.example.laomao.kotlinplayer.ui.activity.AboutActivity
import org.jetbrains.anko.startActivity

/**
 * User: laomao
 * Date: 2018-03-01
 * Time: 11-23
 *
 */
class SettingFragment: PreferenceFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        addPreferencesFromResource(R.xml.setting)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        when(preference?.key){
            resources.getString(R.string.key_about)->{
                startActivity<AboutActivity>()
            }
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }
}