package com.example.vibedemo.model

import com.example.vibedemo.model.response.MusicResponse
import com.example.vibedemo.model.service.MusicSearchService
import io.reactivex.Single

class DataModelImpl(private val service: MusicSearchService):DataModel{

    override fun getData(): Single<MusicResponse> {
        return service.getMusic()
    }
}