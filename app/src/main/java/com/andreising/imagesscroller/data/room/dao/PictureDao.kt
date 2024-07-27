package com.andreising.imagesscroller.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andreising.imagesscroller.data.room.entity.PictureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDao {
    @Query("SELECT * FROM pictures")
    fun getAll(): Flow<List<PictureEntity>>

    @Query("SELECT * FROM pictures WHERE id IS :id")
    fun getItem(id: Int): PictureEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(pictureEntity: PictureEntity)

    @Delete
    suspend fun deleteItem(pictureEntity: PictureEntity)
}