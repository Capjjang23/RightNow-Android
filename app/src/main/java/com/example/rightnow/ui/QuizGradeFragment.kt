package com.example.rightnow.ui

import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.R
import com.example.rightnow.databinding.FragmentQuizGradeBinding

class QuizGradeFragment: BaseFragment<FragmentQuizGradeBinding>(R.layout.fragment_quiz_grade) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        val grade = MyApplication.prefs.getString("grade","00")

        binding.tvGrade.text = "당신의 점수는 $grade 점입니다"
        binding.button2.setOnClickListener {
            navController.navigate(R.id.action_quizGradeFragment2_to_startFragment)
        }
    }

}