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
    var list: ArrayList<Int>,
    var audioRecord: AudioRecorder,
    var apiManager: RecordApiManager?,
    var viewLifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {


    inner class ViewHolder(itemViewBinding: QuizItemBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var img = itemViewBinding.ivQuizImg
        var hint = itemViewBinding.tvHint
        var alpha = itemViewBinding.tvAlpha
        var backBtn = itemViewBinding.btnBack
        var state = itemViewBinding.tvState
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

        // 처음 녹음
        if (holder.alpha.text.isEmpty()){
            // 녹음 파일 이름
            val filePath = Environment.getExternalStorageDirectory().absolutePath+"/Download/"+ Date().time.toString()+".aac"

            // 녹음 시작
            apiManager?.let { audioRecord.startRecording(filePath, it) }
            Log.d("[mmihye] startRecording : ", filePath)
        }

        // 알파벳 값이 변경될때 마다 호출
        apiManager?.resultLivedata?.observe(viewLifecycleOwner){
            holder.alpha.text = it
            Log.d("[mmihye]","값 "+it+"으로 변경")


//            Thread.sleep(1500)

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


        }


        // 알파벳 지우기
        holder.backBtn.setOnClickListener {
//            val before_alpha = holder.alpha.text
//            holder.alpha.text = before_alpha.substring(0, before_alpha.length - 1)
//
//            if(holder.alpha.text.isEmpty()){
//                holder.backBtn.visibility = View.INVISIBLE
//                holder.alpha.visibility = View.INVISIBLE
//                holder.hint.visibility = View.VISIBLE
//            }else{
//                holder.backBtn.visibility = View.VISIBLE
//                holder.alpha.visibility = View.VISIBLE
//                holder.hint.visibility = View.INVISIBLE
//            }
            val filePath = Environment.getExternalStorageDirectory().absolutePath+"/Download/"+ Date().time.toString()+".aac"

            Log.d("[mmihye]","녹음시작")
            // 녹음 시작
            apiManager?.let { audioRecord.startRecording(filePath, it) }

        }




        // 정답저장
        MyApplication.prefs.setString("myAnswer", holder.alpha.text.toString())
    }
}