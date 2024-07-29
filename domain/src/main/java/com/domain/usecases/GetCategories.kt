package com.domain.usecases

import com.domain.repositories.CategoriesRepo

class GetCategories(private val categoriesRepo: CategoriesRepo) {
    /**
     * The invoke operator allows instances of the GetCategories class
     * to be called as if they were functions. This means that instead of
     * calling getCategoriesInstance.invoke(),
     * you can simply call getCategoriesInstance().
     *
     * operator keyword specifies that we want to do operator overloading.
     *
     *  the suspend keyword is used to mark a function that can be paused and resumed
     *  at a later time. These functions can only be called from within a
     *  coroutine or another suspend function.
     */
    suspend operator fun invoke() = categoriesRepo.getCategories()
}