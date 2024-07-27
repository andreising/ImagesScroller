package com.andreising.imagesscroller.domain.usecases

import com.andreising.imagesscroller.data.retrofit.repository.PictureRemoteRepository
import com.andreising.imagesscroller.data.room.repository.PictureLocalRepository
import com.andreising.imagesscroller.domain.model.PictureModel
import com.andreising.imagesscroller.domain.model.toPictureEntity
import com.andreising.imagesscroller.domain.model.toPictureModel
import com.andreising.imagesscroller.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

typealias DownloadState = Resource<List<PictureModel>>

class PicturesFromRemoteUseCase @Inject constructor(
    private val pictureRemoteRepository: PictureRemoteRepository,
    private val pictureLocalRepository: PictureLocalRepository
) {

    private val _downloadState = MutableStateFlow<DownloadState>(Resource.Loading())
    val downloadState: Flow<DownloadState> = _downloadState

    suspend fun onLikeButtonPressed(pictureModel: PictureModel, index: Int) {
        if (pictureModel.isFavourite) pictureLocalRepository.deleteItem(pictureModel.toPictureEntity())
        else pictureLocalRepository.insertItem(pictureModel.toPictureEntity())
        updateFavouriteStatusList(index)
    }


    suspend fun getNewPictureList() {
        try {
            _downloadState.emit(Resource.Loading())
            val result = pictureRemoteRepository.getNewPictureList().map {
                withContext(Dispatchers.IO){
                    it.toPictureModel()
                        .copy(isFavourite = pictureLocalRepository.isItemInDatabase(it.id))
                }
            }
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

    private suspend fun updateFavouriteStatusList(index: Int) = withContext(Dispatchers.IO) {
        var pictureList: MutableList<PictureModel>? = null
        downloadState.first {
            if (it is Resource.Success) pictureList = it.data?.toMutableList()
            true
        }
        pictureList?.let { list ->
            val oldItem = list[index]
            val newItem =
                oldItem.copy(isFavourite = pictureLocalRepository.isItemInDatabase(oldItem.id))
            list.set(index, newItem)
            _downloadState.emit(Resource.Success(list))
        }
    }
}