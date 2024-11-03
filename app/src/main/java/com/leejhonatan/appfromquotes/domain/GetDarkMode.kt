package com.leejhonatan.appfromquotes.domain

import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject

class GetDarkMode @Inject constructor() {
    private var isDarkMode: Boolean = true
    operator fun invoke (){
        if (isDarkMode){
            isDarkMode = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            isDarkMode = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}