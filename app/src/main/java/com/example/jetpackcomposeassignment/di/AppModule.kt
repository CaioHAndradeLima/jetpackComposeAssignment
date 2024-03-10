package com.example.jetpackcomposeassignment.di

import com.example.jetpackcomposeassignment.common.CatUrl
import com.example.jetpackcomposeassignment.feature_listcat.data.repository_remote.CatRepository
import com.example.jetpackcomposeassignment.feature_listcat.data.repository_remote.CatApi
import com.example.jetpackcomposeassignment.feature_listcat.domain.CatUseCases
import com.example.jetpackcomposeassignment.feature_listcat.domain.repository.CatRepositoryImpl
import com.example.jetpackcomposeassignment.feature_listcat.domain.use_case.GetCat
import com.example.jetpackcomposeassignment.feature_listcat.presentation.cats.CatViewModel
import com.example.jetpackcomposeassignment.feature_listcat.domain.use_case.GetCats
import com.example.jetpackcomposeassignment.feature_listcat.presentation.detail.CatDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): CatApi {
        return Retrofit.Builder()
            .baseUrl(CatUrl.API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatRepository(api: CatApi): CatRepository {
        return CatRepositoryImpl(api)
    }

    @Provides
    fun provideViewModel(cases: CatUseCases): CatViewModel {
        return CatViewModel(cases)
    }

    @Provides
    @Singleton
    fun provideCatUseCase(repository: CatRepository): CatUseCases {
        return CatUseCases(
            getCats = GetCats(repository),
            getOneCat = GetCat(repository)
        )
    }

    @Provides
    fun provideDetailViewModel(cases: CatUseCases): CatDetailViewModel {
        return CatDetailViewModel(cases)
    }

}