package com.m2f.app.di

import com.m2f.app.presentation.pagination.PaginationSelectionActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentsModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindsPaginationSelectionActivity(): PaginationSelectionActivity
}