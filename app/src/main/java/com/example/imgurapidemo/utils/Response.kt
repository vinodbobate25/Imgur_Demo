package com.example.imgurapidemo.utils

sealed class Response<out T> {
        data class Success<T>(val data: T): Response<T>()
        data class Error<T>(val message: String,val  data: T? = null): Response<T>()
        data class Loading< out T>(val loading:String) : Response<T>()
}