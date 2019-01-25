package com.m2f.app.main.worker

import android.app.ActivityManager
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.m2f.app.di.Provider
import com.m2f.app.domain.model.AnalyticsPerformance
import com.mobilejazz.kotlin.core.domain.interactor.PutInteractor
import com.mobilejazz.kotlin.core.ext.Dates
import com.mobilejazz.kotlin.core.repository.query.VoidQuery
import javax.inject.Inject

class PerformanceProcessorWorker(ctx: Context, workerParams: WorkerParameters) : Worker(ctx, workerParams) {

    init {
        Provider.appComponent?.inject(this)
    }
    
    @Inject
    lateinit var putAnalyticsModelInteractor: PutInteractor<AnalyticsPerformance>

    override fun doWork(): Result {
        val actManager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)

        val analyticsPerformance =
            AnalyticsPerformance(Dates.now().time, memInfo.totalMem, memInfo.availMem, memInfo.lowMemory)

        return try {
            putAnalyticsModelInteractor(analyticsPerformance, VoidQuery).get()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

}