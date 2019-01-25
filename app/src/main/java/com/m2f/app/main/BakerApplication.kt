package com.m2f.app.main

import android.os.StrictMode
import com.m2f.app.BuildConfig
import com.m2f.app.di.DaggerApplicationComponent
import com.m2f.app.di.initInjection
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BakerApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder()
            .context(this.applicationContext)
            .create(this)

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().apply {
                detectAll()
                penaltyLog()
            }.build())

            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().apply {
                detectAll()
                penaltyLog()
            }.build())

            Stetho.initializeWithDefaults(this)
        }

        initInjection()
    }
}