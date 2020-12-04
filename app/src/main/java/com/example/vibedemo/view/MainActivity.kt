package com.example.vibedemo.view

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.vibedemo.R
import com.example.vibedemo.base.BaseKotlinActivity
import com.example.vibedemo.databinding.ActivityMainBinding
import com.example.vibedemo.view.fragment.*
import com.example.vibedemo.viewmodel.MainViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseKotlinActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
    private lateinit var player: SimpleExoPlayer
    override fun initStartView() {


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
            supportFragmentManager.beginTransaction().replace(viewDataBinding.mainScreen.id, fragment).commit()
        })
        viewModel.statePlayer.observe(this,Observer{
            viewDataBinding.isSlidingUp = it
            if(it == true)
                viewDataBinding.mainPlayer.mainPlayerBottom.player = player
            else
                viewDataBinding.miniPlayer.controller.player = player
        })
        viewModel.musicPlayerLiveData.observe(this, Observer { it ->
            player = it
        })
        // Music Data setting( title, artist, album-image, lyrics, player(main, mini) )
        viewModel.musicResponseLiveData.observe(this, Observer {
            it.let { music ->
                // mini
                viewDataBinding.miniPlayer.music = music
                viewDataBinding.mainPlayer.music = music

                Picasso.with(this).load(music.image).into(viewDataBinding.mainPlayer.albumStandard)

                //뮤직 플레이어
                val mediaSource: MediaSource =
                    viewModel.buildMediaSource(Uri.parse(music.file), this)
                player.setMediaSource(mediaSource)
                player.prepare()
            }
        })
        viewDataBinding.mainPlayer.viewModel = viewModel
    }

    override fun initAfterBinding() {
        viewDataBinding.mainBottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            viewModel.selectMenu(menuItem)
            true
        }
        viewDataBinding.mainBottomNavigation.selectedItemId = R.id.menu_navi_today
        viewDataBinding.slidingLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {}
            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState
            ) {
                viewModel.setStatePlayer(newState)
            }
        })
        viewModel.initPlayer(this.applicationContext)
        viewModel.getMusicSearch()


    }
}