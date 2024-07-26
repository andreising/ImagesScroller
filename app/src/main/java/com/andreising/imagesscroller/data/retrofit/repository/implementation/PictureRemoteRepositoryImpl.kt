package com.andreising.imagesscroller.data.retrofit.repository.implementation

import android.net.http.HttpException
import com.andreising.imagesscroller.data.retrofit.common.Constants
import com.andreising.imagesscroller.data.retrofit.remote.PictureAPI
import com.andreising.imagesscroller.data.retrofit.remote.dto.DTOImageInfo
import com.andreising.imagesscroller.data.retrofit.repository.PictureRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class PictureRemoteRepositoryImpl(private val pictureAPI: PictureAPI) : PictureRemoteRepository {

    private val pictureList = mutableListOf<DTOImageInfo>()

    private val randomIdList = mutableListOf<Int>()

    private var nextId = 0

    override suspend fun getNewPictureList(): List<DTOImageInfo> = withContext(Dispatchers.IO) {
        createNewIdList()
        updatePictureList()
    }

    override suspend fun updatePictureList(): List<DTOImageInfo> = withContext(Dispatchers.IO) {
        val lastId =
            if (nextId + Constants.PICTURE_IN_STEP > Constants.MAX_PICTURE) Constants.MAX_PICTURE
            else nextId + Constants.PICTURE_IN_STEP
        val result = (nextId until lastId).map { id ->
            async { getPictureById(randomIdList[id]) }
        }.awaitAll().filterNotNull()

        pictureList.addAll(result)
        nextId = lastId
        pictureList
    }

    private fun createNewIdList() {
        randomIdList.apply {
            clear()
            addAll(0 until Constants.MAX_PICTURE)
            shuffle()
        }
    }

    private suspend fun getPictureById(id: Int): DTOImageInfo? {
        return try {
            pictureAPI.getImageInfo(id)
        } catch (e: Exception) {
            return null
        }
    }

}