package com.example.rightnow.ui

import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.rightnow.R
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
                binding.tvHansung.visibility = View.INVISIBLE
                if(MyApplication.prefs.getString("myAnswer", "null") == "cat"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="고양이는 영어로 cat 입니다."
            }
            "dog" -> {
                binding.tvHansung.visibility = View.INVISIBLE
                if(MyApplication.prefs.getString("myAnswer", "null") == "dog"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }

                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="강아지는 영어로 dog 입니다."
            }
            "elephant"->{
                binding.tvHansung.visibility = View.INVISIBLE
                if(MyApplication.prefs.getString("myAnswer", "null") == "elephant"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="코끼리는 영어로 elephant 입니다"
            }
            "hansung"->{
                binding.tvHansung.visibility = View.VISIBLE
                if(MyApplication.prefs.getString("myAnswer", "null") == "hansung"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="이 로고의 학교는 hansung 입니다."
            }

        }

        binding.imageButton2.setOnClickListener{
            dismiss()
        }

    }
}