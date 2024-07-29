package com.data.models

data class QuizDto(
    val response_code: Int,
    val results: List<QuestionDto>
)