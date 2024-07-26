package com.andreising.imagesscroller.data.retrofit.repository

import com.andreising.imagesscroller.data.retrofit.remote.dto.DTOImageInfo

interface PictureRemoteRepository {
    suspend fun getNewPictureList(): List<DTOImageInfo>
    suspend fun updatePictureList(): List<DTOImageInfo>
}