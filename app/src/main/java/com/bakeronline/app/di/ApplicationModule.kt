package com.bakeronline.app.di

import com.bakeronline.app.di.viewmodelinjection.ViewModelModule
import dagger.Module

@Module(includes = [ViewModelModule::class, NavigationModule::class])
class ApplicationModule