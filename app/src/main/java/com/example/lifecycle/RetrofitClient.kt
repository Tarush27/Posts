package com.example.lifecycle

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofit = Retrofit.Builder().client(OkHttpClientInterceptor.okHttpClient)
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val service = retrofit.create(PostService::class.java)

}