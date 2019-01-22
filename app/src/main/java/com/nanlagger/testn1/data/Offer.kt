package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Offer(
    @SerializedName("_id") val id: Long,
    @SerializedName("photos") val photos: List<Photo>,
    @SerializedName("params") val params: Params
) : Serializable