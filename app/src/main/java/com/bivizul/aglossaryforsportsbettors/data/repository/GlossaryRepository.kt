package com.bivizul.aglossaryforsportsbettors.data.repository

import com.bivizul.aglossaryforsportsbettors.data.CapiService
import com.bivizul.aglossaryforsportsbettors.data.Content
import com.bivizul.aglossaryforsportsbettors.data.model.Glossary
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.Response
import javax.inject.Inject

class GlossaryRepository @Inject constructor(private val capiService: CapiService){

//    val glossary = flow{
//        emit(capiService.getGlossary())
//    }.flowOn(Dispatchers.IO)

    private val _glossary = MutableSharedFlow<Content<Glossary>>()
    val glossary : SharedFlow<Content<Glossary>> = _glossary.asSharedFlow()

    suspend fun getGlossary(){
        _glossary.emit(Content.Loading())
        val response = capiService.getGlossary()
        if (response.isSuccessful){
            response.body()?.let {
                _glossary.emit(Content.Success(it))
            }
        } else{
            _glossary.emit(Content.Error(response.message()))
        }
    }
}