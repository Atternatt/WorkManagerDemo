package com.bakeronline.app.data.network

import com.bakeronline.app.data.retrypolicy.addIncrementalRetryPolicy
import com.bakeronline.app.domain.BO
import com.bakeronline.app.main.exceptions.*
import com.jakewharton.retrofit2.adapter.rxjava2.Result
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import org.reactivestreams.Publisher
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class FlowableResultTransformer<T : DTO> : FlowableTransformer<Result<T>, BO> {

    companion object {
        private const val MAX_RETRIES: Int = 7
    }

    override fun apply(upstream: Flowable<Result<T>>): Publisher<BO> {
        return upstream.flatMap {
            if (it.isError) {
                with(it.error()) {
                    if (this is IOException) {
                        when (this) {
                            is ConnectException -> throw NoNetworkException()
                            is TimeoutException -> throw NetworkTimeoutException()
                            is UnknownHostException -> throw ServerDownException()
                            else -> throw UnhandledException(this.toString())
                        }
                    } else {
                        throw UnhandledException(this.toString())
                    }
                }
            } else {
                val response = it.response()
                if (!response.isSuccessful) {
                    val code: Int = response.code()
                    //fixme: 1) check if server sends some errorBody
                    //fixme: 2) if so check how to serialize it using a self contained error object
                    //fixme: 3) if possible trying to abstract it withou knowing about retrofit
                    /*it.response().errorBody()?.let {
                        val dtoError: FolioErrorDTO = retrofit.parseError(it)
                        throw dtoError.error
                    }*/

                    if (retryableCode(code)) {
                        throw GenericRetryableException()
                    } else {
                        throw BakerOnlineException(ErrorCode(response.message() ?: "", code))
                    }
                } else {
                    //todo: this needs to be tested
                    Flowable.just(it.response().body()!!.toBO())
                }
            }
        }.addIncrementalRetryPolicy(MAX_RETRIES)
    }


    private fun retryableCode(code: Int): Boolean {
        return code == 500 || code == 504 || code == 505
    }
}

fun <T : DTO> Flowable<Result<T>>.extractResponse(): Flowable<BO> = compose(FlowableResultTransformer())