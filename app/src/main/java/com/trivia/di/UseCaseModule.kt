package com.trivia.di

import com.domain.repositories.CategoriesRepo
import com.domain.repositories.QuizRepo
import com.domain.usecases.GetCategories
import com.domain.usecases.GetQuizUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetCategoriesUseCase(categoriesRepo: CategoriesRepo): GetCategories =
        GetCategories(categoriesRepo)

    @Provides
    fun providesGetQuizUseCase(quizRepo: QuizRepo): GetQuizUseCase =
        GetQuizUseCase(quizRepo)
}
