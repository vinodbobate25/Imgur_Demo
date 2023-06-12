package com.example.imgurapidemo.di

import com.example.imgurapidemo.domain.repository.GalleryRepository
import com.example.imgurapidemo.network.repository.GalleryRepositoryImpl
import com.example.imgurapidemo.network.service.GalleryService
import com.example.imgurapidemo.utils.Const.CLIENT_ID
import com.example.imgurapidemo.utils.Const.WEB_API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Named("WEB_API")
    fun provideWebAPI(): String = WEB_API

    @Provides
    fun provideRetrofit(
        @Named("WEB_API") webAPI: String,
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(webAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideGalleryService(
        retrofit: Retrofit
    ): GalleryService = retrofit.create(GalleryService::class.java)

    @Provides
    fun provideGalleryRepository(
        galleryService: GalleryService,
    ): GalleryRepository = GalleryRepositoryImpl(
        galleryService = galleryService)

}