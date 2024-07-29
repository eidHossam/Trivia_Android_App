package com.domain.repositories

import com.domain.models.CategoriesResponse

interface CategoriesRepo {
    suspend fun getCategories() : CategoriesResponse
}