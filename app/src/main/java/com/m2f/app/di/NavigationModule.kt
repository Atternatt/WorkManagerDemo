package com.m2f.app.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.pure.AppNavigator
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun providesCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun providesRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun providesNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @Singleton
    fun providesNavigatorFactory(): NavigatorFactory = object : NavigatorFactory {
        override fun createNavigator(activity: Activity, containerId: Int): Navigator {
            return AppNavigator(activity, containerId)
        }
    }

    //class to assist injection for navigator
    interface NavigatorFactory {
        fun createNavigator(activity: Activity, containerId: Int): Navigator
    }
}

