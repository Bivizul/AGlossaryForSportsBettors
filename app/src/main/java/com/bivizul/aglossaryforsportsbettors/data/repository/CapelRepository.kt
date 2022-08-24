package com.bivizul.aglossaryforsportsbettors.data.repository

import com.bivizul.aglossaryforsportsbettors.data.CapiService
import com.bivizul.aglossaryforsportsbettors.data.Content
import com.bivizul.aglossaryforsportsbettors.data.model.GetCapel
import com.bivizul.aglossaryforsportsbettors.data.model.SetCapel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class CapelRepository @Inject constructor(private val capiService: CapiService) {

    private val _capel = MutableSharedFlow<Content<GetCapel>>()
    val capel: SharedFlow<Content<GetCapel>> = _capel.asSharedFlow()

    suspend fun getCapel(setCapel: SetCapel) {
        _capel.emit(Content.Loading())
        val response = capiService.getCapel(setCapel)
        if (response.isSuccessful) {
            response.body()?.let {
                _capel.emit(Content.Success(it))
            }
        } else {
            _capel.emit(Content.Error(response.message()))
        }
    }

}