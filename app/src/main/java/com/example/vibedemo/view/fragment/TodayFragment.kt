package com.example.vibedemo.view.fragment

import com.example.vibedemo.R
import com.example.vibedemo.base.BaseKotlinFragment
import com.example.vibedemo.databinding.FragmentMainTodayBinding
import com.example.vibedemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodayFragment : BaseKotlinFragment<FragmentMainTodayBinding, MainViewModel>() {
    private val TAG = "TodayFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_main_today

    override val viewModel: MainViewModel by sharedViewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = TodayFragment()
    }
}