package com.andreising.imagesscroller.data.retrofit.remote

import com.andreising.imagesscroller.data.retrofit.remote.dto.DTOImageInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface PictureAPI {
    @GET("id/{id}/info")
    suspend fun getImageInfo(@Path("id") id: Int): DTOImageInfo
}