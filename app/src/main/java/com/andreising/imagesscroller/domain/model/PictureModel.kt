package com.andreising.imagesscroller.domain.model

import com.andreising.imagesscroller.data.retrofit.remote.dto.DTOImageInfo
import com.andreising.imagesscroller.data.room.entity.PictureEntity

data class PictureModel(
    val id: Int,
    val path: String,
    val isFavourite: Boolean = false
)

fun DTOImageInfo.toPictureModel(): PictureModel = PictureModel(
    id = this.id,
    path = this.download_url
)

fun PictureEntity.toPictureModel(): PictureModel =
    PictureModel(id = this.id, path = this.localPath, isFavourite = true)

fun PictureModel.toPictureEntity(): PictureEntity =
    PictureEntity(id = this.id, localPath = this.path)