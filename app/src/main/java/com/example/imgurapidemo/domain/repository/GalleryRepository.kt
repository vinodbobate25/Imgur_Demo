package com.example.imgurapidemo.domain.repository

import com.example.imgurapidemo.domain.model.GalleryResponse
import com.example.imgurapidemo.utils.Response
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getGallery(): Flow<Response<GalleryResponse>>

}