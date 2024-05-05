package com.sopt.now.jumpit

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class JumpitApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}