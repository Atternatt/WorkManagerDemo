package com.m2f.app.di

import android.content.Context
import com.m2f.app.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton
import java.lang.reflect.Type

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesChuckInterceptor(context: Context): ChuckInterceptor = ChuckInterceptor(context.applicationContext)

    @Provides
    @Singleton
    fun providesHttpClient(context: Context, chuckInterceptor: ChuckInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        addNetworkInterceptor(StethoInterceptor())
                        addNetworkInterceptor(chuckInterceptor)
                    }
                    cache(Cache(context.getDir("network_cache", Context.MODE_PRIVATE), 10 * 1024 * 1024))
                }
                .build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(httpClient: OkHttpClient): Retrofit {

        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
                override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
        }


        return Retrofit.Builder()
                .addConverterFactory(nullOnEmptyConverterFactory)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .baseUrl(BuildConfig.ENDPOINT)
                .build()
    }
}