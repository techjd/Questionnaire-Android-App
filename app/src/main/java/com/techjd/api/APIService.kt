package com.techjd.api

import com.Constantc
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    val instance: responses_store

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(Constantc.URL)
            .addConverterFactory(GsonConverterFactory.create())

            .build()
        instance = retrofit.create(responses_store::class.java)
    }
}