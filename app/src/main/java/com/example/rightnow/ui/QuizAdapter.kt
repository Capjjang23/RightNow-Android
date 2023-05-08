package com.example.rightnow.ui

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
        var hint = itemViewBinding.tvHint
        var alpha = itemViewBinding.tvAlpha
        var backBtn = itemViewBinding.btnBack
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


        // 알파벳 지우기
        holder.backBtn.setOnClickListener {
            val before_alpha = holder.alpha.text
            holder.alpha.text = before_alpha.substring(0, before_alpha.length - 1)

            if(holder.alpha.text.isEmpty()){
                holder.backBtn.visibility = View.INVISIBLE
                holder.alpha.visibility = View.INVISIBLE
                holder.hint.visibility = View.VISIBLE
            }else{
                holder.backBtn.visibility = View.VISIBLE
                holder.alpha.visibility = View.VISIBLE
                holder.hint.visibility = View.INVISIBLE
            }
        }


        // 서버 전송 받을 시 확인
        if(holder.alpha.text.isEmpty()){
            holder.backBtn.visibility = View.INVISIBLE
            holder.alpha.visibility = View.INVISIBLE
            holder.hint.visibility = View.VISIBLE
        }else{
            holder.backBtn.visibility = View.VISIBLE
            holder.alpha.visibility = View.VISIBLE
            holder.hint.visibility = View.INVISIBLE
        }

        // 정답저장
        MyApplication.prefs.setString("myAnswer", holder.alpha.text.toString())
    }
}