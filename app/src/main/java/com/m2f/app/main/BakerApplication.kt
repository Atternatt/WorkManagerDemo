package com.m2f.app.main

import android.os.StrictMode
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.facebook.stetho.Stetho
import com.m2f.app.BuildConfig
import com.m2f.app.di.DaggerApplicationComponent
import com.m2f.app.di.Provider
import com.m2f.app.di.initInjection
import com.m2f.app.main.worker.PerformanceProcessorWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import java.util.concurrent.TimeUnit


class BakerApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val builder = DaggerApplicationComponent.builder()
        builder.application(this)
        builder.seedInstance(this)
        val appComponent = builder.build()

        appComponent.inject(this)
        Provider.appComponent = appComponent
        return appComponent
    }

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

        val constraints = Constraints.Builder()
            .setRequiresStorageNotLow(true)
            .build()

        // Periodic worker to save an snapshot of the memory
        val periodicWorkRequest = PeriodicWorkRequestBuilder<PerformanceProcessorWorker>(10, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        // Enqueue the periodic worker into the pool
        WorkManager.getInstance().enqueue(periodicWorkRequest)
    }
}