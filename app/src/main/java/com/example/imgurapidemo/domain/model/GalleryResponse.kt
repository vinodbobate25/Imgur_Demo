package com.example.imgurapidemo.domain.model

import com.google.gson.annotations.SerializedName

data class GalleryResponse(
    @field:SerializedName("data")
    val listData: List<ImageData>,
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("success")
    val success: Boolean
)