package com.andreising.imagesscroller.domain.usecases

import com.andreising.imagesscroller.data.room.repository.PictureLocalRepository
import com.andreising.imagesscroller.domain.model.PictureModel
import com.andreising.imagesscroller.domain.model.toPictureEntity
import com.andreising.imagesscroller.domain.model.toPictureModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PicturesFromLocalUseCase@Inject constructor(
    private val repository: PictureLocalRepository
) {

    fun getAllItems(): Flow<List<PictureModel>> {
        return repository.getAll().map { entities->
            entities.map { entity->
                entity.toPictureModel()
            }
        }
    }

    suspend fun deleteItem(item: PictureModel) { repository.deleteItem(item.toPictureEntity()) }

}