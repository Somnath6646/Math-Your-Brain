package com.wenull.mathyourbrain

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MYBApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object{
        val token: String? = null
    }
}