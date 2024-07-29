package com.data.repo

import com.data.remote.ApiService
import com.domain.models.CategoriesResponse
import com.domain.repositories.CategoriesRepo

class CategoriesRepoImpl(private val apiService: ApiService): CategoriesRepo {
    override suspend fun getCategories(): CategoriesResponse = apiService.getCategories()
}