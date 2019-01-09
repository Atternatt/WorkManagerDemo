package com.bakeronline.app.data.retrypolicy

import com.bakeronline.app.main.exceptions.RetryableException
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.Flowables
import java.util.concurrent.TimeUnit

private const val WAITING_TIME_MULTIPLIER = 5

fun <T> Flowable<T>.addIncrementalRetryPolicy(maxRetrys: Int): Flowable<T> = retryWhen {
    Flowables.zip(
        it,
        Flowable.range(1, maxRetrys)
    ) { throwable: Throwable, iteration: Int ->
        if (throwable is RetryableException && iteration <= maxRetrys) {
            Observable.timer((iteration * WAITING_TIME_MULTIPLIER).toLong(), TimeUnit.SECONDS)
        } else {
            throw throwable //propagate the error
        }
    }
}