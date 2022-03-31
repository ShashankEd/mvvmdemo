package com.example.funnymemes.model.api

import com.example.funnymemes.model.response.MemeListResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://api.imgflip.com/get_memes
class MemesWebService {
    private lateinit var api:MemesApi
    init {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.imgflip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofitBuilder.create(MemesApi::class.java)
    }
    suspend fun getMemes(): MemeListResponse {
        return api.getMemes()
    }
    interface MemesApi {
        @GET("get_memes")//end point
        suspend fun getMemes(): MemeListResponse
    }
}