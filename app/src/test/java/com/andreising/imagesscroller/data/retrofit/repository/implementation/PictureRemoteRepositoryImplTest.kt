package com.andreising.imagesscroller.data.retrofit.repository.implementation

import com.andreising.imagesscroller.data.retrofit.common.Constants
import com.andreising.imagesscroller.data.retrofit.remote.PictureAPI
import com.andreising.imagesscroller.data.retrofit.remote.dto.DTOImageInfo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit

class PictureRemoteRepositoryImplTest {

    @get:Rule
    var rule = MockitoJUnit.rule()

    private lateinit var pictureAPI: PictureAPI
    private lateinit var repository: PictureRemoteRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)  // Инициализация моков
        pictureAPI = mock(PictureAPI::class.java)
        repository = PictureRemoteRepositoryImpl(pictureAPI)
    }

    @Test
    fun `getNewPictureList should return new picture list`(): Unit = runBlocking {
        // Mocking PictureAPI to return the expected DTOImageInfo
        val dtoImageInfo = DTOImageInfo(
            id = 0,
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            url = "https://unsplash.com/photos/yC-Yzbqy7PY",
            download_url = "https://picsum.photos/id/0/5000/3333"
        )
        `when`(pictureAPI.getImageInfo(anyInt())).thenReturn(dtoImageInfo)

        // Creating a new picture list
        val pictureList = repository.getNewPictureList()

        // Expected size of the picture list
        val expectedSize = Constants.PICTURE_IN_STEP

        // Checking the size of the returned list
        assertEquals(expectedSize, pictureList.size)

        // Checking that the API was called the expected number of times
        verify(pictureAPI, times(Constants.PICTURE_IN_STEP)).getImageInfo(anyInt())
    }

    @Test
    fun `updatePictureList should add more pictures to the list`(): Unit = runBlocking {
        // Mocking PictureAPI to return the expected DTOImageInfo
        val dtoImageInfo = DTOImageInfo(
            id = 0,
            author = "Alejandro Escamilla",
            width = 5000,
            height = 3333,
            url = "https://unsplash.com/photos/yC-Yzbqy7PY",
            download_url = "https://picsum.photos/id/0/5000/3333"
        )
        `when`(pictureAPI.getImageInfo(anyInt())).thenReturn(dtoImageInfo)

        // Loading the initial picture list
        repository.getNewPictureList()

        // Updating the picture list
        val updatedPictureList = repository.updatePictureList()

        // Expected size of the updated picture list
        val expectedSize = Constants.PICTURE_IN_STEP * 2

        // Checking the size of the updated list
        assertEquals(expectedSize, updatedPictureList.size)

        // Checking that the API was called the expected number of times
        verify(pictureAPI, times(Constants.PICTURE_IN_STEP * 2)).getImageInfo(anyInt())
    }


}