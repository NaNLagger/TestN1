package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("position") val position: Long,
    @SerializedName("original") val source: String?,
    @SerializedName("360x270cp") val preview: String?
) : Serializable