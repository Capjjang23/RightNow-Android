package com.example.rightnow.ui

import android.os.Handler
import android.os.Looper
import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.R
import com.example.rightnow.databinding.FragmentLoadBinding

class LoadFragment  : BaseFragment<FragmentLoadBinding>(R.layout.fragment_load) {
    private val LOADING_TIME = 1000L // 로딩 화면 시간 (ms)

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()

        // 로딩 화면을 일정 시간 동안 보여줌
        Handler(Looper.getMainLooper()).postDelayed({
            // 다음 화면으로 이동
            navController.navigate(R.id.action_loadFragment_to_startFragment)
        }, LOADING_TIME)


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.startScreen.setOnClickListener {
            navController.navigate(R.id.action_loadFragment_to_startFragment)
        }
    }


}