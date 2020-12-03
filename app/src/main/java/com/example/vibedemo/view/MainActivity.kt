package com.example.vibedemo.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.vibedemo.R
import com.example.vibedemo.base.BaseKotlinActivity
import com.example.vibedemo.databinding.ActivityMainBinding
import com.example.vibedemo.view.fragment.*
import com.example.vibedemo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

        viewModel.selectedMenu.observe(this, Observer { menuItemId ->
            var fragment : Fragment? = null
            when (menuItemId) {
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
            supportFragmentManager.beginTransaction().replace(fragment_layout.id, fragment!!).commit()
        })
    }

    override fun initAfterBinding() {
        main_bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            viewModel.selectMenu(menuItem.itemId)
            true
        }
        main_bottom_navigation.selectedItemId = R.id.menu_navi_today
    }
}