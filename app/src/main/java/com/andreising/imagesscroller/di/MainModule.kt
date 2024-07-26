package com.andreising.imagesscroller.di

import com.andreising.imagesscroller.data.retrofit.instance.retrofit
import com.andreising.imagesscroller.data.retrofit.remote.PictureAPI
import com.andreising.imagesscroller.data.retrofit.repository.PictureRemoteRepository
import com.andreising.imagesscroller.data.retrofit.repository.implementation.PictureRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun providePictureApi(): PictureAPI = retrofit

    @Provides
    @Singleton
    fun providePictureRemoteRepository(api: PictureAPI): PictureRemoteRepository{
        return PictureRemoteRepositoryImpl(api)
    }

}