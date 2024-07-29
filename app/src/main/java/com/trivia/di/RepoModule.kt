package com.trivia.di

import com.data.remote.ApiService
import com.data.repo.CategoriesRepoImpl
import com.data.repo.QuizRepoImpl
import com.domain.repositories.CategoriesRepo
import com.domain.repositories.QuizRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun providesCategoriesRepo(apiService: ApiService): CategoriesRepo =
        CategoriesRepoImpl(apiService)

    @Provides
    fun providesQuizRepo(apiService: ApiService): QuizRepo =
        QuizRepoImpl(apiService)
}