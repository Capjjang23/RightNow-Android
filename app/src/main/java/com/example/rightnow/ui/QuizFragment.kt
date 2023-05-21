package com.example.rightnow.ui

import android.media.MediaRecorder
import android.os.Build
import androidx.viewpager2.widget.ViewPager2
import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.common.AudioRecorder
import com.example.rightnow.R
import com.example.rightnow.apiManager.RecordApiManager
import com.example.rightnow.databinding.FragmentQuizBinding
import kotlin.collections.ArrayList


class QuizFragment  : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    var quizItems = arrayListOf<String>("cat","dog","elephant","frog", "gorilla")
    var answer = ""

    private val userVersion:Int = Build.VERSION.SDK_INT
    private lateinit var mediaRecorder:MediaRecorder
    private var outputPath: String? = null
    private var recorder: MediaRecorder? = null
    private var state:Boolean = false

    val audioRecorder = AudioRecorder()
    val apiManager = RecordApiManager.getInstance(context)


    override fun initStartView() {
        super.initStartView()

        MyApplication.prefs.setString("grade", "0")

    }

    override fun initDataBinding() {
        super.initDataBinding()

        // 어댑터 생성
        binding.viewPager2.adapter = QuizAdapter(getCardList(), audioRecorder,apiManager,viewLifecycleOwner)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL


    }



    private fun getCardList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.elephant,
            R.drawable.frog,
            R.drawable.gorilla
            )
    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        val dpValue = 54
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()

        binding.viewPager2.clipToPadding = false
        binding.viewPager2.setPadding(margin, 0, margin, 0)

        binding.dotsIndicator.setViewPager2(binding.viewPager2)



        // 정답 제출
        binding.btnCheck.setOnClickListener {
//            val action = QuizFragmentDirections.actionQuizFragmentToQuizResultDialog(answer)
//            navController.navigate(action)
//            context?.let { it1 -> audioRecorder.restartRecording(it1) }
//            filePath = Environment.getExternalStorageDirectory().absolutePath+"/Download/"+Date().time.toString()+".aac"
//            audioRecorder.startRecording(filePath)

        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                answer =quizItems[binding.viewPager2.currentItem]
            }
        })

    }


}