package com.example.rightnow.ui

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.rightnow.apiManager.RecordApiManager
import com.example.rightnow.common.AudioRecorder
import com.example.rightnow.databinding.QuizItemBinding
import java.util.*
import kotlin.collections.ArrayList

class QuizAdapter(
    var list: ArrayList<Int>
) :
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