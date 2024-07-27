package com.andreising.imagesscroller.presentation.screens.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreising.imagesscroller.domain.model.PictureModel
import com.andreising.imagesscroller.domain.usecases.PicturesFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesScreenViewModel @Inject constructor(
    private val picturesFromLocalUseCase: PicturesFromLocalUseCase
) : ViewModel() {

    val listFlow = picturesFromLocalUseCase.getAllItems()

    fun unlikePicture(pictureModel: PictureModel) = viewModelScope.launch {
        picturesFromLocalUseCase.deleteItem(pictureModel)
    }
}