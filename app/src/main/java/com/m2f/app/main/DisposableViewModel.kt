package com.m2f.app.main

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class DisposableViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}