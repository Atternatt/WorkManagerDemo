package com.m2f.app.domain

import com.m2f.app.domain.executor.ThreadExecutor
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface Interactor {

    fun <Type> Observable<Type>.addThreadPolicy(executor: ThreadExecutor) =
        this.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread())

    fun <Type> Flowable<Type>.addThreadPolicy(executor: ThreadExecutor) =
        this.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread())

    fun <Type> Maybe<Type>.addThreadPolicy(executor: ThreadExecutor) =
        this.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread())

    fun <Type> Single<Type>.addThreadPolicy(executor: ThreadExecutor) =
        this.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread())

    fun Completable.addThreadPolicy(executor: ThreadExecutor) =
        this.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread())
}