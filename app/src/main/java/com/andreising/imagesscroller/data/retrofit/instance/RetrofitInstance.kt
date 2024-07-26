package com.andreising.imagesscroller.data.retrofit.instance

import com.andreising.imagesscroller.data.retrofit.common.Constants
import com.andreising.imagesscroller.data.retrofit.remote.PictureAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(PictureAPI::class.java)