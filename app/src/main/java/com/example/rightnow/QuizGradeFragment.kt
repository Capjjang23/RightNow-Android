package com.example.rightnow

import com.android.example.travalue.base.BaseFragment
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

        binding.button2.setOnClickListener {
            navController.navigate(R.id.action_quizGradeFragment2_to_startFragment)
        }
    }

}