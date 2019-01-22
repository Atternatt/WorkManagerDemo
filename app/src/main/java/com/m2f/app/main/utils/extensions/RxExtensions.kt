package com.m2f.app.main.utils.extensions

import android.os.Looper
import androidx.annotation.CheckResult
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.m2f.app.main.exceptions.BakerOnlineException
import com.m2f.app.main.exceptions.UnhandledException
import io.reactivex.*
import io.reactivex.disposables.Disposable
import timber.log.Timber


/**
 * @author Marc Moreno
 */


private val onNextStub: (Any) -> Unit = {}
private val onCompleteStub: () -> Unit = {}
private val onErrorStub: (BakerOnlineException) -> Unit = {
    Timber.e(it)
}


infix fun <E : FlowableEmitter<*>> E.canEmitt(block: E.() -> Unit) {
    if (this.isCancelled.not()) {
        block()
    }
}

infix fun <E : ObservableEmitter<*>> E.canEmitt(block: E.() -> Unit) {
    if (this.isDisposed.not()) {
        block()
    }
}

private inline fun <reified T : Observable, R : Any> T.observe(crossinline block: (T) -> R): Flowable<R> =
    Flowable.create({ emitter ->
        emitter canEmitt {
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(observable: Observable, id: Int) = try {
                    onNext(block(observable as T))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            }.also {
                setCancellable { removeOnPropertyChangedCallback(it) }
                addOnPropertyChangedCallback(it)
            }
        }
    }, BackpressureStrategy.LATEST)

fun <T : Any> ObservableField<T>.observe(): Flowable<T> = observe { it.get()!! }.share()

fun <T : Any> Single<T>.subsBy(
    onError: (BakerOnlineException) -> Unit = onErrorStub,
    onSuccess: (T) -> Unit = onNextStub
)
        : Disposable = subscribe(
    onSuccess,
    { throwable ->
        onError(
            throwable as? BakerOnlineException
                ?: UnhandledException("Unknown exception: $throwable")
        )
    })

fun <T : Any> Flowable<T>.subsBy(
    onError: (BakerOnlineException) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onNext: (T) -> Unit = onNextStub
)
        : Disposable = subscribe(
    onNext,
    { throwable ->
        onError(
            throwable as? BakerOnlineException
                ?: UnhandledException("Unknown exception: $throwable")
        )
    },
    onComplete
)

fun <T : Any> Maybe<T>.subsBy(
    onError: (BakerOnlineException) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onSuccess: (T) -> Unit = onNextStub
)
        : Disposable = subscribe(
    onSuccess,
    { throwable ->
        onError(
            throwable as? BakerOnlineException
                ?: UnhandledException("Unknown exception: $throwable")
        )
    },
    onComplete
)

fun Completable.subsBy(
    onComplete: () -> Unit = onCompleteStub,
    onError: (BakerOnlineException) -> Unit = onErrorStub
): Disposable =
    subscribe(onComplete, { throwable ->
        onError(
            throwable as? BakerOnlineException
                ?: UnhandledException("Unknown exception: $throwable")
        )
    })

@CheckResult
fun <T> FlowableEmitter<T>.checkMainThread(): Boolean {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        this canEmitt {
            this.onError(
                IllegalStateException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().name
                )
            )
        }
        return false
    }
    return true
}