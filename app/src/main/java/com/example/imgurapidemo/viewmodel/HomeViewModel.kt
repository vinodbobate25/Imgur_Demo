package com.example.imgurapidemo.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imgurapidemo.domain.model.GalleryResponse
import com.example.imgurapidemo.domain.repository.GalleryRepository
import com.example.imgurapidemo.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val galleryRepository: GalleryRepository
) : ViewModel() {
    private val _topGalleryState = mutableStateOf<Response<GalleryResponse>>(Response.Loading(""))
    val topGalleryListState: State<Response<GalleryResponse>> = _topGalleryState

    fun fetchTopGallery() = viewModelScope.launch {
        galleryRepository.getGallery().collect {
            _topGalleryState.value = it
        }
    }

}