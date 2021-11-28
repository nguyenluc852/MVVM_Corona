package com.s.basemvvm

import android.app.Application
import com.s.basemvvm.utils.Logger
import com.s.basemvvm.utils.Pref

 class MyApplication :  Application() {
    override fun onCreate() {
        super.onCreate()
        Pref.init(applicationContext)
        Logger.setDebugging(isDebug())
    }

    /**
     * Set debugging for the whole application.
     */
    fun isDebug():Boolean =false
}