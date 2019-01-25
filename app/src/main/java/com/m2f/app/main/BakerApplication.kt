package com.m2f.app.main

import android.os.StrictMode
import androidx.work.*
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
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = workDataOf("TEST" to "Test")

        val request = OneTimeWorkRequestBuilder<PerformanceProcessorWorker>()
            .setInputData(data)
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()
//
//        val periodicWorkRequest = PeriodicWorkRequestBuilder<TestWorker>(1, TimeUnit.SECONDS)
//            .setInputData(data)
//            .setConstraints(constraints)
//            .build()

        WorkManager.getInstance().enqueue(request)

    }
}