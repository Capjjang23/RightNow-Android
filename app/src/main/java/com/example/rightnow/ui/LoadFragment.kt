package com.example.rightnow.ui

import com.android.example.travalue.base.BaseFragment
import com.example.rightnow.R
import com.example.rightnow.databinding.FragmentLoadBinding

class LoadFragment  : BaseFragment<FragmentLoadBinding>(R.layout.fragment_load) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()

        binding.startScreen.setOnClickListener {
            navController.navigate(R.id.action_loadFragment_to_startFragment)
        }

    }

}