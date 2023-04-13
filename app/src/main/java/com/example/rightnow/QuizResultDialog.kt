package com.example.rightnow

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.rightnow.base.BaseBottomDialogFragment
import com.example.rightnow.databinding.DialogQuizResultBinding

class QuizResultDialog: BaseBottomDialogFragment<DialogQuizResultBinding>(R.layout.dialog_quiz_result) {
    val args: QuizResultDialogArgs by navArgs()

    override fun initDataBinding() {
        super.initDataBinding()

    }
    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.button.setOnClickListener {
            navController.navigate(R.id.action_quizResultDialog_to_quizGradeFragment2)
        }


        when (args.argsString) {
            "cat" -> {
                binding.tvAnswer.text="고양이는 영어로  cat입니다."
            }
            "dog" -> {
                binding.tvAnswer.text="강아지는 영어로  dog입니다."
            }
            "elephant"->{
                binding.tvAnswer.text="코끼리는 영어로  elephant입니다."
            }
            "frog"->{
                binding.tvAnswer.text="개구리는 영어로  frog입니다."
            }
            "gorilla"->{
                binding.button.visibility= View.VISIBLE
                binding.tvAnswer.text="고릴라는 영어로  gorilla입니다."
            }
        }

        binding.imageButton2.setOnClickListener{
            dismiss()
        }

    }
}