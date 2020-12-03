package com.example.vibedemo.di

import com.example.vibedemo.model.DataModel
import com.example.vibedemo.model.DataModelImpl
import com.example.vibedemo.model.service.MusicSearchService
import com.example.vibedemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var retrofitPart = module {
    single<MusicSearchService> {
        Retrofit.Builder()
            .baseUrl("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MusicSearchService::class.java)
    }
}

var adapterPart = module {
    factory {
        //adapter...
    }
}

var modelPart = module {
    factory<DataModel> {
        DataModelImpl(get())
    }
}

var viewModelPart = module {
    viewModel {
        MainViewModel(get())
    }
}

var myDiModule = listOf(retrofitPart, adapterPart, modelPart, viewModelPart)