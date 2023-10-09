package com.picpay.desafio.android

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(apiUrl: String, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(apiUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .build()
}

inline fun <reified T> createApi(retrofit: Retrofit): T = retrofit.create(T::class.java)