package com.trivia.ui.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.domain.models.TriviaCategory
import com.trivia.databinding.CategoryItemBinding

class CategoriesAdapter(private val clickListener: (TriviaCategory) -> Unit) :
    ListAdapter<TriviaCategory, CategoriesAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    open class ViewHolder(private val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(triviaCategory: TriviaCategory, clickListener: (TriviaCategory) -> Unit) {
            itemBinding.categoryButton.text = triviaCategory.name
            itemBinding.categoryButton.setOnClickListener{ clickListener(triviaCategory) }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<TriviaCategory>() {
        override fun areItemsTheSame(oldItem: TriviaCategory, newItem: TriviaCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TriviaCategory, newItem: TriviaCategory): Boolean {
            return oldItem == newItem
        }
    }
}