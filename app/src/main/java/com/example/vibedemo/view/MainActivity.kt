package com.example.vibedemo.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.vibedemo.R
import com.example.vibedemo.base.BaseKotlinActivity
import com.example.vibedemo.databinding.ActivityMainBinding
import com.example.vibedemo.view.fragment.*
import com.example.vibedemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {
        //Main-Layout에서만 사용되므로 View-binding으로 제공
        viewDataBinding.mainBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            viewModel.selectMenu(menuItem)
            true
        }
    }

    override fun initDataBinding() {

        viewModel.selectedMenu.observe(this, Observer { menuItem ->
            var fragment : Fragment = TodayFragment()
            when (menuItem.itemId) {
                R.id.menu_navi_today -> {
                    fragment = TodayFragment()
                }
                R.id.menu_navi_chart -> {
                    fragment = ChartFragment()
                }
                R.id.menu_navi_video -> {
                    fragment = VideoFragment()
                }
                R.id.menu_navi_search -> {
                    fragment = SearchFragment()
                }
                R.id.menu_navi_library -> {
                    fragment = MyFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(binding.fragmentLayout.id, fragment).commit()
        })
    }

    override fun initAfterBinding() {
        viewDataBinding.mainBottomNavigation.selectedItemId = R.id.menu_navi_today
    }
}