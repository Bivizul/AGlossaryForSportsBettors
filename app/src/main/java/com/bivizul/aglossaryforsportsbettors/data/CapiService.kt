package com.bivizul.aglossaryforsportsbettors.data

import com.bivizul.aglossaryforsportsbettors.data.model.GetCapel
import com.bivizul.aglossaryforsportsbettors.data.model.Glossary
import com.bivizul.aglossaryforsportsbettors.data.model.SetCapel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CapiService {

    @GET("24AGlossaryForSportsBettors/glossary.json")
    suspend fun getGlossary(): Response<Glossary>

    @POST("24AGlossaryForSportsBettors/capel.php")
    suspend fun getCapel(@Body setCapel: SetCapel): Response<GetCapel>

}