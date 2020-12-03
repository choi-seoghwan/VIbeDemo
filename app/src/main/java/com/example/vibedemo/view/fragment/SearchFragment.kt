package com.example.vibedemo.view.fragment

import com.example.vibedemo.R
import com.example.vibedemo.base.BaseKotlinFragment
import com.example.vibedemo.databinding.FragmentMainSearchBinding
import com.example.vibedemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : BaseKotlinFragment<FragmentMainSearchBinding, MainViewModel>() {
    private val TAG = "SearchFragment"

    override val layoutResourceId: Int
        get() = R.layout.fragment_main_search

    override val viewModel: MainViewModel by sharedViewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = ChartFragment()
    }
}