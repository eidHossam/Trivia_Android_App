package com.trivia.ui.quiz_screen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.trivia.R
import com.trivia.databinding.ActivityQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.format

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {
    private val quizViewModel: QuizViewModel by viewModels()
    private lateinit var binding: ActivityQuizBinding
    private lateinit var answersAdapter: AnswersAdapter
    private var questionNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //End the quiz when the back button is pressed
        binding.backBtn.setOnClickListener { finish() }

        //Skip to the next question
        binding.nextQuestionBtn.setOnClickListener { quizViewModel.nextQuestion() }

        quizViewModel.getQuiz(intent.getIntExtra("categoryID", 0))

        initAnswersRecyclerView()

        subscribeToObservers()
    }

    private fun initAnswersRecyclerView() {
        val answersRV = binding.answersRV
        answersRV.layoutManager = LinearLayoutManager(this)

        answersAdapter = AnswersAdapter(answersRV)
        answersRV.adapter = answersAdapter
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            quizViewModel.question.collect {
                if (it != null) {
                    binding.quizTitle.text = it.category    //Set the current question category
                    binding.questionTV.text = it.question   //Set the question
                    binding.progressBar.progress += 10      //Update the progress bar
                    binding.questionNumber.text = format("Question %d/10", questionNumber)
                    questionNumber++

                    answersAdapter.submitList(null)
                    answersAdapter.submitList(it.answers)
                    answersAdapter.correctAnswerIndex = it.correctAnswerIndex
                }
            }
        }

        lifecycleScope.launch {
            quizViewModel.isQuizDone.collect {
                if (it) {
                    showQuizScoreDialog()
                }
            }
        }
    }

    private fun showQuizScoreDialog() {
        val quizScore = answersAdapter.score

        val quizScoreBuilder = AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
            .setMessage("You scored $quizScore out of 10")
            .setPositiveButton("Finish") { dialog, _ ->
                dialog.dismiss()
                finish()
            }

        quizScoreBuilder.setOnCancelListener{ finish() }

        val dialog = quizScoreBuilder.create()
        dialog.show()

        val messageView = dialog.findViewById<TextView>(android.R.id.message)
        messageView?.setTextColor(ContextCompat.getColor(this, R.color.black))

    }
}