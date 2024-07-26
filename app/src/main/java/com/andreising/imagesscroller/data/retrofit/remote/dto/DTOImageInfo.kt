package com.andreising.imagesscroller.data.retrofit.remote.dto

data class DTOImageInfo(
    val id: Int,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
)