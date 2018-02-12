package com.example.jaeyoungpark.dialogplayground

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by jaeyoungpark on 12/02/2018.
 */
class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}