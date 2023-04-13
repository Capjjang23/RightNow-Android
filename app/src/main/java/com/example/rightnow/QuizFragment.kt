package com.example.rightnow

import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import androidx.navigation.NavDirections
import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.databinding.FragmentQuizBinding
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList


class QuizFragment  : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    var quizItems = arrayListOf<String>("cat","dog","elephant","frog", "gorilla")
    var answer = ""

    private val userVersion:Int = Build.VERSION.SDK_INT
    private lateinit var mediaRecorder:MediaRecorder
    private var outputPath: String? = null
    private var recorder: MediaRecorder? = null
    private var state:Boolean = false

    @RequiresApi(Build.VERSION_CODES.S)
    override fun initStartView() {
        super.initStartView()

    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun initDataBinding() {
        super.initDataBinding()

        binding.viewPager2.adapter = QuizAdapter(getCardList()) // 어댑터 생성
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

        binding.btnCheck.setOnClickListener {
            val action = QuizFragmentDirections.actionQuizFragmentToQuizResultDialog(answer)
            navController.navigate(action)
        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                answer =quizItems[binding.viewPager2.currentItem]
            }
        })

    }


}