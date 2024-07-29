package com.trivia.ui.quiz_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.models.QuestionModel
import com.domain.models.QuizModel
import com.domain.usecases.GetQuizUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase
) : ViewModel() {

    private lateinit var _quiz : QuizModel

    private val _question : MutableStateFlow<QuestionModel?> = MutableStateFlow(null)
    val question : StateFlow<QuestionModel?> = _question

    private var _currentQuestionIndex = 0

    private val _isQuizDone : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isQuizDone : StateFlow<Boolean> = _isQuizDone

    fun getQuiz(categoryID: Int) {
        viewModelScope.launch {
            try {
                _quiz = getQuizUseCase(categoryID)

                nextQuestion()
            } catch (e: Exception) {
                Log.e("QuizViewModel", "Error fetching quiz: ${e.message}")
            }
        }
    }

    fun nextQuestion()
    {
        if (_currentQuestionIndex < _quiz.size) {
            _question.value = _quiz[_currentQuestionIndex]
            _currentQuestionIndex++
        }else{
            _isQuizDone.value = true
        }
    }
}