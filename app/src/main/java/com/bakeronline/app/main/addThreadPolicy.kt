package com.bakeronline.app.main

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Marc Moreno
 * @since 9/1/2019.
 */

fun <Type> Observable<Type>.addThreadPolicy() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <Type> Flowable<Type>.addThreadPolicy() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <Type> Maybe<Type>.addThreadPolicy() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun <Type> Single<Type>.addThreadPolicy() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
fun Completable.addThreadPolicy() = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())