package com.example.vibedemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vibedemo.base.BaseKotlinViewModel
import com.example.vibedemo.model.DataModel

class MainViewModel(private val model: DataModel) : BaseKotlinViewModel() {

    // Main Tab
    private val TAG = "MainViewModel"

    private val _selectedMenu = MutableLiveData<Int>()
    val selectedMenu: LiveData<Int>
        get() = _selectedMenu

    fun selectMenu(menuItemId: Int) {
        if (selectedMenu.value != menuItemId) {
            _selectedMenu.postValue(menuItemId)
        }
    }
}