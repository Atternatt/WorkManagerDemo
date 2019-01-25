package com.m2f.app.di

import com.m2f.app.presentation.pagination.AdvancedPaginationActivity
import com.m2f.app.presentation.pagination.JetpackPaginationActivity
import com.m2f.app.presentation.pagination.PaginationSelectionActivity
import com.m2f.app.presentation.pagination.simple.SimplePaginationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentsModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindsPaginationSelectionActivity(): PaginationSelectionActivity

    @ContributesAndroidInjector
    abstract fun bindsSimplePaginationActivity(): SimplePaginationActivity

    @ContributesAndroidInjector
    abstract fun bindsAdvancedPaginationActivity(): AdvancedPaginationActivity

    @ContributesAndroidInjector
    abstract fun bindsJetpackPaginationActivity(): JetpackPaginationActivity


}