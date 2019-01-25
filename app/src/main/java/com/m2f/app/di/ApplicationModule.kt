package com.m2f.app.di

import android.app.Application
import android.content.Context
import com.m2f.app.data.executor.JobExecutor
import com.m2f.app.data.pagination.RandomUUIDGenerator
import com.m2f.app.di.viewmodelinjection.ViewModelModule
import com.m2f.app.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, NavigationModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesRandomUUIDGenerator(): RandomUUIDGenerator = RandomUUIDGenerator(20)

    @Provides
    @Singleton
    fun providesThreadExecutor(): ThreadExecutor = JobExecutor()
}