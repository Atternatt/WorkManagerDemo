package com.m2f.app.di

import com.m2f.app.di.viewmodelinjection.ViewModelBuilder
import com.m2f.app.di.viewmodelinjection.ViewModelModule
import com.m2f.app.main.BakerApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ViewModelBuilder::class]
)
interface ApplicationComponent : AndroidInjector<BakerApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<BakerApplication>()

}