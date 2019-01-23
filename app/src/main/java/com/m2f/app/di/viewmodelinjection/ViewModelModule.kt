package com.m2f.app.di.viewmodelinjection

import androidx.lifecycle.ViewModel
import com.m2f.app.presentation.pagination.PaginationSelectionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PaginationSelectionViewModel::class)
    abstract fun bindPaginationViewModel(paginationSelectionViewModel: PaginationSelectionViewModel): ViewModel
}