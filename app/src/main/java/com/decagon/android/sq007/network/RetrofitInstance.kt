package com.decagon.android.sq007.network

import com.decagon.android.sq007.utilities.ConstantUtils.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            . build()
}
    val api: RetroServiceInterface by lazy {
      retrofit.create(RetroServiceInterface::class.java)
    }
}