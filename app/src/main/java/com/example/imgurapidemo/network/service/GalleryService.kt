package com.example.imgurapidemo.network.service

import com.example.imgurapidemo.domain.model.GalleryResponse
import com.example.imgurapidemo.utils.Const.CLIENT_ID
import com.example.imgurapidemo.utils.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GalleryService {

    @GET("top/{page}")
    suspend fun getAllImages(
        @Path("page") page: Int,
        @Header("Client-ID") clientId: String =CLIENT_ID,
    ) : GalleryResponse
}