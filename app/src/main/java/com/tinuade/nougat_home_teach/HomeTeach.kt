package com.tinuade.nougat_home_teach

import android.app.Application
import android.os.SystemClock

class HomeTeach : Application() {
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(4000)
    }
}