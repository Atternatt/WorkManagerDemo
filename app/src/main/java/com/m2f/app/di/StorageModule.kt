package com.m2f.app.di

import android.content.Context
import com.m2f.app.domain.model.AnalyticsModel
import com.m2f.app.domain.model.AnalyticsPerformance
import com.mobilejazz.kotlin.core.repository.DeleteRepository
import com.mobilejazz.kotlin.core.repository.GetRepository
import com.mobilejazz.kotlin.core.repository.PutRepository
import com.mobilejazz.kotlin.core.repository.datasource.file.FileStreamValueDataStorage
import com.mobilejazz.kotlin.core.repository.datasource.toDeleteRepository
import com.mobilejazz.kotlin.core.repository.datasource.toGetRepository
import com.mobilejazz.kotlin.core.repository.datasource.toPutRepository
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton


@Module
class StorageModule {

    //region Analytics Model Repositories
    //region Datasource
    @Provides
    @Singleton
    fun provideAnalyticsModelFileStreamValueDataSource(context: Context): FileStreamValueDataStorage<AnalyticsModel> {
        val filePath = context.filesDir.absolutePath + "analytics-model.txt"
        return FileStreamValueDataStorage(File(filePath))
    }
    //endregion

    //region Repository
    @Provides
    @Singleton
    fun provideAnalyticsModelFileStreamValueGetRepository(dataSource: FileStreamValueDataStorage<AnalyticsModel>): GetRepository<AnalyticsModel> {
        return dataSource.toGetRepository()
    }

    @Provides
    @Singleton
    fun provideAnalyticsModelFileStreamValuePutRepository(dataSource: FileStreamValueDataStorage<AnalyticsModel>): PutRepository<AnalyticsModel> {
        return dataSource.toPutRepository()
    }

    @Provides
    @Singleton
    @AnalyticsModelDeleteRepository
    fun provideAnalyticsModelFileStreamValueDeleteRepository(dataSource: FileStreamValueDataStorage<AnalyticsModel>): DeleteRepository {
        return dataSource.toDeleteRepository()
    }
    //endregion
    //endregion

    //region Analytics Performance Repositories
    //region DataSources
    @Provides
    @Singleton
    fun provideAnalyticsPerformanceFileStreamValueDataSource(context: Context): FileStreamValueDataStorage<AnalyticsPerformance> {
        val filePath = context.filesDir.absolutePath + "analytics-performance.txt"
        return FileStreamValueDataStorage(File(filePath))
    }

    //region Repositories
    @Provides
    @Singleton
    fun provideAnalyticsPerformanceFileStreamValueGetRepository(dataSource: FileStreamValueDataStorage<AnalyticsPerformance>): GetRepository<AnalyticsPerformance> {
        return dataSource.toGetRepository()
    }

    @Provides
    @Singleton
    fun provideAnalyticsPerformanceFileStreamValuePutRepository(dataSource: FileStreamValueDataStorage<AnalyticsPerformance>): PutRepository<AnalyticsPerformance> {
        return dataSource.toPutRepository()
    }

    @Provides
    @Singleton
    @AnalyticsPerformanceDeleteRepository
    fun provideAnalyticsPerformanceFileStreamValueDeleteRepository(dataSource: FileStreamValueDataStorage<AnalyticsPerformance>): DeleteRepository {
        return dataSource.toDeleteRepository()
    }
    //endregion
    //endregion
    //endregion
}