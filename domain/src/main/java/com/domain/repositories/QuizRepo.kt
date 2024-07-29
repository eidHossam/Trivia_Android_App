package com.domain.repositories

import com.domain.models.QuizModel

interface QuizRepo {
    suspend fun getQuiz(categoryID: Int) : QuizModel
}