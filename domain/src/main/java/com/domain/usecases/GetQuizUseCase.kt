package com.domain.usecases

import com.domain.repositories.QuizRepo

class GetQuizUseCase(private val quizRepo: QuizRepo) {

    suspend operator fun invoke(categoryID: Int) = quizRepo.getQuiz(categoryID)
}