package com.bivizul.aglossaryforsportsbettors.data

sealed class Content<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Loading<T> : Content<T>()
    class Success<T>(data: T?) : Content<T>(data = data)
    class Error<T>(message: String?) : Content<T>(message = message)
}