package com.example.vibedemo.viewmodel

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vibedemo.base.BaseKotlinViewModel
import com.example.vibedemo.model.DataModel

class MainViewModel(private val model: DataModel) : BaseKotlinViewModel() {

    // Main Tab
    private val TAG = "MainViewModel"

    private val _selectedMenu = MutableLiveData<MenuItem>()
    val selectedMenu: LiveData<MenuItem>
        get() = _selectedMenu

    fun selectMenu(item : MenuItem) {
        if (selectedMenu.value != item) {
            _selectedMenu.postValue(item)
        }
    }
}