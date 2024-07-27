package com.andreising.imagesscroller.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pictures")
data class PictureEntity(
    @PrimaryKey val id: Int,
    val localPath: String
)
