package com.andreising.imagesscroller.data.room.repository

import com.andreising.imagesscroller.data.room.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

interface PictureLocalRepository {
    fun getAll(): Flow<List<PictureEntity>>

    suspend fun insertItem(pictureEntity: PictureEntity)

    suspend fun deleteItem(pictureEntity: PictureEntity)

    suspend fun isItemInDatabase(id: Int): Boolean
}