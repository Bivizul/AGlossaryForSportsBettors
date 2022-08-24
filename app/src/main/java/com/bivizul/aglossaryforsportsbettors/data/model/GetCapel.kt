package com.bivizul.aglossaryforsportsbettors.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GetCapel(
    @SerializedName("url")
    val getCapel: String,
)