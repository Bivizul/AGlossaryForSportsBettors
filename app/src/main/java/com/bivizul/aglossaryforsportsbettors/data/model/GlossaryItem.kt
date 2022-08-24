package com.bivizul.aglossaryforsportsbettors.data.model

import androidx.annotation.Keep

@Keep
data class GlossaryItem(
    val id: Int,
    val name: String,
    val description: String,
    var check: Boolean = true,
)