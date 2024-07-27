package com.andreising.imagesscroller.di

import android.app.Application
import androidx.room.Room
import com.andreising.imagesscroller.data.retrofit.instance.retrofit
import com.andreising.imagesscroller.data.retrofit.remote.PictureAPI
import com.andreising.imagesscroller.data.retrofit.repository.PictureRemoteRepository
import com.andreising.imagesscroller.data.retrofit.repository.implementation.PictureRemoteRepositoryImpl
import com.andreising.imagesscroller.data.room.PictureDataBase
import com.andreising.imagesscroller.data.room.repository.PictureLocalRepository
import com.andreising.imagesscroller.data.room.repository.implementation.PictureLocalRepositoryImpl
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
    fun provideMainDatabase(app: Application): PictureDataBase {
        return Room.databaseBuilder(
            app,
            PictureDataBase::class.java,
            "picture_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePictureApi(): PictureAPI = retrofit

    @Provides
    @Singleton
    fun providePictureRemoteRepository(api: PictureAPI): PictureRemoteRepository =
        PictureRemoteRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePictureLocalRepository(
        app: Application,
        dataBase: PictureDataBase
    ): PictureLocalRepository =
        PictureLocalRepositoryImpl(app, dataBase.pictureDao())

}