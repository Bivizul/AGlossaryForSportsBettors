package com.bivizul.aglossaryforsportsbettors.di

import com.bivizul.aglossaryforsportsbettors.data.CapiService
import com.bivizul.aglossaryforsportsbettors.data.Capiconst.CAPI_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CapiModule {

    @Provides
    fun provideCapiUrl() = CAPI_URL

    @Provides
    @Singleton
    fun provideCapifit(capiUrl: String) = Retrofit.Builder()
        .baseUrl(capiUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CapiService::class.java)

}