package com.example.rightnow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rightnow.databinding.QuizItemBinding

class QuizAdapter(var list: ArrayList<Int>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {


    inner class ViewHolder(itemViewBinding: QuizItemBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var img = itemViewBinding.ivQuizImg
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            QuizItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(list[position])

    }
}