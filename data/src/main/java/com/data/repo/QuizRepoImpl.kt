package com.data.repo

import com.data.mapper.QuizDtoToModelMapper
import com.data.models.QuizDto
import com.data.remote.ApiService
import com.domain.models.QuizModel
import com.domain.repositories.QuizRepo

class QuizRepoImpl (private val apiService: ApiService) : QuizRepo {
    override suspend fun getQuiz(categoryID: Int): QuizModel {
        val quiz: QuizDto = if (categoryID == -1)
            apiService.getRandomQuiz(10)
        else
            apiService.getCategoryQuiz(10, categoryID)

        return QuizDtoToModelMapper().map(quiz)
    }
}