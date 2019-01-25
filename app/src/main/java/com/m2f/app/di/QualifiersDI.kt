package com.m2f.app.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AnalyticsPerformanceDeleteRepository

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AnalyticsModelDeleteRepository
