package com.example.imgurapidemo.network.repository

import android.util.Log
import com.example.imgurapidemo.domain.model.GalleryResponse
import com.example.imgurapidemo.domain.repository.GalleryRepository
import com.example.imgurapidemo.network.service.GalleryService
import com.example.imgurapidemo.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepositoryImpl @Inject constructor(
    private val galleryService: GalleryService,
):GalleryRepository {
    override fun getGallery(): Flow<Response<GalleryResponse>> =flow{
        lateinit var img:GalleryResponse
        kotlin.runCatching {
           img= galleryService.getAllImages(1)
        }.onSuccess {
            Log.d("success","dsds")
            emit(Response.Success(img))
        }
            .onFailure {
                Log.d("error in api ",""+it.toString())
                if (it is IOException) {
                    emit(Response.Error("Network Failure"))
                } else {
                    emit(Response.Error("Conversion Error"))
                }
            };


    }
}