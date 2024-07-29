package com.trivia.ui.quiz_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trivia.R
import com.trivia.databinding.AnswerItemBinding

class AnswersAdapter(private val recyclerView: RecyclerView) :
    ListAdapter<String, AnswersAdapter.ViewHolder>(QuizDiffCallback()) {
    var correctAnswerIndex: Int = 0

    private var _score: Int = 0
    val score: Int
        get() = _score

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            AnswerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding, recyclerView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        //reset the colors of the background
        holder.itemView.findViewById<TextView>(R.id.answerTV).setBackgroundResource(R.color.white)
    }

    inner class ViewHolder(
        private val itemBinding: AnswerItemBinding, private val recyclerView: RecyclerView
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(answer: String) {
            val answerDecoded =
                HtmlCompat.fromHtml(answer, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
            itemBinding.answerTV.text = answerDecoded
            itemBinding.answerTV.setOnClickListener {
                if (adapterPosition == correctAnswerIndex) {
                    itemBinding.answerTV.setBackgroundResource(R.color.correctAnswerBG)
                    _score++
                } else {
                    itemBinding.answerTV.setBackgroundResource(R.color.wrongAnswerBG)
                    recyclerView.findViewHolderForAdapterPosition(correctAnswerIndex)?.itemView?.findViewById<TextView>(
                        R.id.answerTV
                    )?.setBackgroundResource(R.color.correctAnswerBG)
                }

                disableClickListeners()
            }
        }

        private fun disableClickListeners() {
            for (i in 0 until recyclerView.childCount) {
                recyclerView.getChildAt(i).findViewById<TextView>(R.id.answerTV)
                    .setOnClickListener(null)
            }
        }
    }

    class QuizDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}