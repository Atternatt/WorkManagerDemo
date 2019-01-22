package com.m2f.app.di

import com.m2f.app.di.viewmodelinjection.ViewModelModule
import dagger.Module

@Module(includes = [ViewModelModule::class, NavigationModule::class])
class ApplicationModule