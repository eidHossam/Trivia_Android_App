package com.data.remote

import com.data.models.QuizDto
import com.domain.models.CategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    /**
     * Gets all the supported trivia questions categories
     */
    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponse

    /**
     * Returns a random quiz of a random category
     *
     * @param amount : Number of question to return
     */
    @GET("api.php?")
    suspend fun getRandomQuiz(@Query("amount") amount: Int): QuizDto

    /**
     * Returns a quiz based on the specified category
     *
     * @param amount : Number of questions to return
     * @param categoryID : The ID of the required category
     */
    @GET("api.php?")
    suspend fun getCategoryQuiz(
        @Query("amount") amount: Int,
        @Query("category") categoryID: Int
    ): QuizDto
}