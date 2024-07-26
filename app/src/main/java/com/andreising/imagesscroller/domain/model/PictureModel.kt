package com.andreising.imagesscroller.domain.model

import com.andreising.imagesscroller.data.retrofit.remote.dto.DTOImageInfo

data class PictureModel(
    val url: String,
    val isFavourite: Boolean = false
)

fun DTOImageInfo.toPictureModel(): PictureModel = PictureModel(url = this.download_url)