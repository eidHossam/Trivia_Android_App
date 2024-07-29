package com.trivia.ui.main_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.models.CategoriesResponse
import com.domain.models.QuizModel
import com.domain.usecases.GetCategories
import com.domain.usecases.GetQuizUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategories,
    private val getQuizUseCase: GetQuizUseCase
) :
    ViewModel() {

    private val _categories: MutableStateFlow<CategoriesResponse?> = MutableStateFlow(null)
    val categories: StateFlow<CategoriesResponse?> = _categories

    fun getCategories() {
        viewModelScope.launch {
            try {
                _categories.value = getCategoriesUseCase()
            } catch (e: Exception) {
                Log.e("CategoriesViewModel", "Error fetching categories: ${e.message}")
            }
        }
    }


}