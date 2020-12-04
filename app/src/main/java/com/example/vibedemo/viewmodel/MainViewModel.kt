package com.example.vibedemo.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vibedemo.base.BaseKotlinViewModel
import com.example.vibedemo.model.DataModel
import com.example.vibedemo.model.response.MusicResponse
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val model: DataModel) : BaseKotlinViewModel() {

    // Main Tab
    private val TAG = "MainViewModel"

    private val _selectedMenu = MutableLiveData<MenuItem>()
    val selectedMenu: LiveData<MenuItem>
        get() = _selectedMenu

    fun selectMenu(item: MenuItem) {
        if (selectedMenu.value != item) {
            _selectedMenu.postValue(item)
        }
    }

    private val _statePlayer = MutableLiveData<Boolean>()
    val statePlayer: LiveData<Boolean>
        get() = _statePlayer

    fun setStatePlayer(currentState: SlidingUpPanelLayout.PanelState) {
        // Collapse = False
        // Expend = True
        val state = currentState != SlidingUpPanelLayout.PanelState.COLLAPSED
        if (state != _statePlayer.value)
            _statePlayer.postValue(state)
    }


    // player
    private val _musicPlayerLiveData = MutableLiveData<SimpleExoPlayer>()
    val musicPlayerLiveData: LiveData<SimpleExoPlayer>
        get() = _musicPlayerLiveData


    fun initPlayer(context: Context) {
        _musicPlayerLiveData.postValue(SimpleExoPlayer.Builder(context).build())
    }

    fun setPlayer(player: SimpleExoPlayer) {
        _musicPlayerLiveData.postValue(player)
    }

    // music
    private val _musicResponseLiveData = MutableLiveData<MusicResponse>()
    val musicResponseLiveData: LiveData<MusicResponse>
        get() = _musicResponseLiveData

    fun getMusicSearch() {
        addDisposable(
            model.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.run {
                        _musicResponseLiveData.postValue(this)
                    }
                }, {
                    Log.d(TAG, "response error, message : ${it.message}")
                })
        )
    }
    internal fun buildMediaSource(uri: Uri, context: Context): MediaSource {
        var userAgent: String = Util.getUserAgent(context, "project_name")
        return ExtractorMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent))
            .createMediaSource(
                uri
            )
    }
    fun onLikeButtonClick() {

    }
}