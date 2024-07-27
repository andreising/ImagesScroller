package com.andreising.imagesscroller.data.room.repository.implementation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.andreising.imagesscroller.data.room.dao.PictureDao
import com.andreising.imagesscroller.data.room.entity.PictureEntity
import com.andreising.imagesscroller.data.room.repository.PictureLocalRepository
import kotlinx.coroutines.flow.Flow
import java.io.File

class PictureLocalRepositoryImpl(private val context: Context, private val dao: PictureDao) : PictureLocalRepository {

    override fun getAll(): Flow<List<PictureEntity>> = dao.getAll()

    override suspend fun insertItem(pictureEntity: PictureEntity) {
        val localPath = saveImageLocally(pictureEntity.localPath, pictureEntity.id)
        dao.insertItem(pictureEntity.copy(localPath = localPath))
    }

    override suspend fun deleteItem(pictureEntity: PictureEntity) {
        dao.deleteItem(pictureEntity)
    }

    override suspend fun isItemInDatabase(id: Int): Boolean {
        return try {
            val result = dao.getItem(id)
            result != null
        } catch (e: Exception) {
            Log.e("PictureLocalRepositoryImpl", "Error checking item in database", e)
            false
        }
    }

    private suspend fun saveImageLocally(url: String, id: Int): String {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false) // Disable hardware bitmaps.
            .build()

        val result = (loader.execute(request) as SuccessResult).drawable
        val bitmap = (result as BitmapDrawable).bitmap

        val file = File(context.filesDir, "image_$id.jpg") // Изменено на filesDir
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, it)
        }
        return file.absolutePath
    }
}