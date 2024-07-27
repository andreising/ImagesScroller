package com.andreising.imagesscroller.data.room.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreising.imagesscroller.data.room.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

interface PictureLocalRepository {
    fun getAll(): Flow<List<PictureEntity>>

    suspend fun insertItem(pictureEntity: PictureEntity)

    suspend fun deleteItem(pictureEntity: PictureEntity)

    suspend fun isItemInDatabase(id: Int): Boolean
}