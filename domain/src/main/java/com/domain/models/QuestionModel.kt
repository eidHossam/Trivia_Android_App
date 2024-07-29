package com.domain.models

data class QuestionModel(
    val category: String,
    val question: String,
    val correctAnswerIndex: Int,
    val answers: ArrayList<String>
)
