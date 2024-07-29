package com.data.mapper

import androidx.core.text.HtmlCompat
import com.data.models.QuizDto
import com.domain.models.QuestionModel
import com.domain.models.QuizModel
import kotlin.random.Random

class QuizDtoToModelMapper {

    /**
     * Change the quiz model from the API response model to the app layer model
     */
    fun map(quiz: QuizDto): QuizModel {
        val questionsList: ArrayList<QuestionModel> = ArrayList()

        for (question in quiz.results) {
            val correctAnswerIndex: Int = Random.nextInt(0, question.incorrect_answers.size + 1)

            val answers: ArrayList<String> = question.incorrect_answers.toCollection(ArrayList())
            answers.add(correctAnswerIndex, question.correct_answer)

            questionsList.add(
                QuestionModel(
                    HtmlCompat.fromHtml(question.category, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString(),
                    HtmlCompat.fromHtml(question.question, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString(),
                    correctAnswerIndex,
                    answers
                )
            )
        }

        return QuizModel(questionsList)
    }
}