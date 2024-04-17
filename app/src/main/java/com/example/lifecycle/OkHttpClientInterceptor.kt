package com.example.lifecycle

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpClientInterceptor {
    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

}