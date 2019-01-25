package com.m2f.app.di

import com.m2f.app.domain.model.AnalyticsModel
import com.m2f.app.domain.model.AnalyticsPerformance
import com.mobilejazz.kotlin.core.domain.interactor.*
import com.mobilejazz.kotlin.core.repository.DeleteRepository
import com.mobilejazz.kotlin.core.repository.GetRepository
import com.mobilejazz.kotlin.core.repository.PutRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    //region Analytics Model Interactors
    @Provides
    @Singleton
    fun provideGetAllAnalyticsModelInteractor(getRepository: GetRepository<AnalyticsModel>): GetAllInteractor<AnalyticsModel> {
        return getRepository.toGetAllInteractor()
    }

    @Provides
    @Singleton
    fun providePutAnalyticsModelInteractor(putRepository: PutRepository<AnalyticsModel>): PutInteractor<AnalyticsModel> {
        return putRepository.toPutInteractor()
    }

    @Provides
    @Singleton
    fun provideDeleteAllAnalyticsModelInteractor(@AnalyticsModelDeleteRepository deleteRepository: DeleteRepository): DeleteAllInteractor {
        return deleteRepository.toDeleteAllInteractor()
    }
    //endregion

    //region Analytics Performance Interactors
    @Provides
    @Singleton
    fun provideGetAllAnalyticsPerformanceInteractor(getRepository: GetRepository<AnalyticsPerformance>): GetAllInteractor<AnalyticsPerformance> {
        return getRepository.toGetAllInteractor()
    }

    @Provides
    @Singleton
    fun providePutAnalyticsPerformanceInteractor(putRepository: PutRepository<AnalyticsPerformance>): PutInteractor<AnalyticsPerformance> {
        return putRepository.toPutInteractor()
    }

    @Provides
    @Singleton
    fun provideDeleteAllAnalyticsPerformanceInteractor(@AnalyticsPerformanceDeleteRepository deleteRepository: DeleteRepository): DeleteAllInteractor {
        return deleteRepository.toDeleteAllInteractor()
    }
    //endregion
}