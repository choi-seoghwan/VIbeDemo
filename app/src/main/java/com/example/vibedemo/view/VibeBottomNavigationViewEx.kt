package com.example.vibedemo.view

import android.content.Context
import android.util.AttributeSet
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class VibeBottomNavigationViewEx : BottomNavigationViewEx {

    constructor(paramContext: Context?) : super(paramContext) {
        init()
    }

    constructor(paramContext: Context?, paramAttributeSet: AttributeSet?) : super(paramContext, paramAttributeSet) {
        init()
    }

    constructor(paramContext: Context?, paramAttributeSet: AttributeSet?, paramInt: Int) : super(paramContext, paramAttributeSet, paramInt) {
        init()
    }

    private fun init() {
        enableAnimation(false)
        labelVisibilityMode = 1
        isItemHorizontalTranslationEnabled = false
    }
}