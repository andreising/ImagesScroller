package com.andreising.imagesscroller.domain.usecases

import com.andreising.imagesscroller.data.retrofit.repository.PictureRemoteRepository
import com.andreising.imagesscroller.domain.model.PictureModel
import com.andreising.imagesscroller.domain.model.toPictureModel
import com.andreising.imagesscroller.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

typealias DownloadState = Resource<List<PictureModel>>

class GetPicturesFromRemoteUseCase @Inject constructor(
    private val pictureRemoteRepository: PictureRemoteRepository
) {

    private val _downloadState = MutableStateFlow<DownloadState>(Resource.Loading())
    val downloadState: Flow<DownloadState> = _downloadState

    suspend fun getNewPictureList() {
        try {
            _downloadState.emit(Resource.Loading())
            val result = pictureRemoteRepository.getNewPictureList().map { it.toPictureModel() }
            _downloadState.emit(Resource.Success(result))
        } catch (e: Exception) {
            _downloadState.emit(Resource.Error(message = "Something went wrong ${e.message}"))
        }
    }

    suspend fun updatingPictureList() {
        try {
            val newList = pictureRemoteRepository.updatePictureList().map { it.toPictureModel() }
            _downloadState.emit(Resource.Success(newList))
        } catch (e: Exception) {
            _downloadState.emit(Resource.Error(message = "Something went wrong ${e.message}"))
        }
    }
}