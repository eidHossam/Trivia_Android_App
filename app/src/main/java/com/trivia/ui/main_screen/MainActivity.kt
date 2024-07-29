package com.trivia.ui.main_screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.domain.models.TriviaCategory
import com.trivia.databinding.ActivityMainBinding
import com.trivia.ui.quiz_screen.QuizActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private lateinit var categoriesAdapter : CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        categoriesViewModel.getCategories()

        initCategoriesRecyclerView()

        subscribeToObservers()
    }

    private fun startQuiz(category : TriviaCategory)
    {
        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("categoryID", category.id)
        intent.putExtra("categoryName", category.name)

        startActivity(intent)
    }

    private fun initCategoriesRecyclerView() {
        val categoriesRV: RecyclerView = binding.categoriesRV
        categoriesRV.layoutManager = GridLayoutManager(this, 2)

        categoriesAdapter = CategoriesAdapter(this::startQuiz)
        categoriesRV.adapter = categoriesAdapter
    }

    private fun subscribeToObservers() {
        lifecycleScope.launch {
            categoriesViewModel.categories.collect {
                categoriesAdapter.submitList(it?.trivia_categories)
            }
        }
    }
}