package com.andreising.imagesscroller.presentation.screens.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreising.imagesscroller.domain.usecases.GetPicturesFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteScreenViewModel @Inject constructor(
    private val getPicturesFromRemoteUseCase: GetPicturesFromRemoteUseCase
): ViewModel() {

    val statusFlow = getPicturesFromRemoteUseCase.downloadState

    private val _isLoadingMore = MutableStateFlow(false)
    val isLoadingMore: StateFlow<Boolean> = _isLoadingMore

    fun getNewList() = viewModelScope.launch{ getPicturesFromRemoteUseCase.updatingPictureList() }

    fun updateList() = viewModelScope.launch {
        _isLoadingMore.value = true
        getPicturesFromRemoteUseCase.updatingPictureList()
        _isLoadingMore.value = false
    }

    fun onFavouritesButtonPressed() {

    }

    init {
        viewModelScope.launch { getPicturesFromRemoteUseCase.getNewPictureList() }
    }
}