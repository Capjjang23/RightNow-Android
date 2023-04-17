package com.example.rightnow

import android.annotation.SuppressLint
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
                if(MyApplication.prefs.getString("myAnswer", "null") == "cat"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvAnswer.text="고양이는 영어로  cat입니다."
            }
            "dog" -> {
                if(MyApplication.prefs.getString("myAnswer", "null") == "dog"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }

                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvAnswer.text="강아지는 영어로  dog입니다."
            }
            "elephant"->{
                if(MyApplication.prefs.getString("myAnswer", "null") == "elephant"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvAnswer.text="코끼리는 영어로  elephant입니다."
            }
            "frog"->{
                if(MyApplication.prefs.getString("myAnswer", "null") == "frog"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvAnswer.text="개구리는 영어로  frog입니다."
            }
            "gorilla"->{
                if(MyApplication.prefs.getString("myAnswer", "null") == "gorilla"){
                    binding.tvCheck.text ="정답입니다!"
                    var currentGrade = MyApplication.prefs.getString("grade", "0").toInt()
                    currentGrade += 20
                    MyApplication.prefs.setString("grade",currentGrade.toString())
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.button.visibility= View.VISIBLE
                binding.tvAnswer.text="고릴라는 영어로  gorilla입니다."
            }
        }

        binding.imageButton2.setOnClickListener{
            dismiss()
        }

    }
}