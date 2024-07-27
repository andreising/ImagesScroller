package com.andreising.imagesscroller.presentation.screens.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreising.imagesscroller.domain.model.PictureModel
import com.andreising.imagesscroller.domain.usecases.PicturesFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteScreenViewModel @Inject constructor(
    private val picturesFromRemoteUseCase: PicturesFromRemoteUseCase
) : ViewModel() {

    val statusFlow = picturesFromRemoteUseCase.downloadState

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore

    fun getNewList() = viewModelScope.launch { picturesFromRemoteUseCase.updatingPictureList() }

    fun updateList() = viewModelScope.launch {
        _isLoadingMore.value = true
        picturesFromRemoteUseCase.updatingPictureList()
        _isLoadingMore.value = false
    }

    fun onFavouritesButtonPressed(pictureModel: PictureModel, index: Int) = viewModelScope.launch {
        picturesFromRemoteUseCase.onLikeButtonPressed(pictureModel, index)
    }

    init {
        viewModelScope.launch { picturesFromRemoteUseCase.getNewPictureList() }
    }
}