package com.example.vibedemo.model

import com.example.vibedemo.model.response.MusicResponse
import io.reactivex.Single

interface DataModel {
    fun getData(): Single<MusicResponse>
}