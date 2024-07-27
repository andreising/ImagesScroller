package com.andreising.imagesscroller.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreising.imagesscroller.data.room.dao.PictureDao
import com.andreising.imagesscroller.data.room.entity.PictureEntity

@Database(entities = [PictureEntity::class], version = 1)
abstract class PictureDataBase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao
}