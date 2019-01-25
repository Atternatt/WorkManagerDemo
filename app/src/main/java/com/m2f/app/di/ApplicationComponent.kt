package com.m2f.app.di

import android.app.Application
import com.m2f.app.di.viewmodelinjection.ViewModelBuilder
import com.m2f.app.di.viewmodelinjection.ViewModelModule
import com.m2f.app.main.BakerApplication
import com.m2f.app.main.worker.PerformanceProcessorWorker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

object Provider {
    var appComponent: ApplicationComponent? = null
}

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        StorageModule::class,
        InteractorModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ViewModelBuilder::class]
)
interface ApplicationComponent : AndroidInjector<BakerApplication> {

    fun inject(performanceProcessorWorker: PerformanceProcessorWorker)

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BakerApplication>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        abstract override fun build(): ApplicationComponent
    }

}