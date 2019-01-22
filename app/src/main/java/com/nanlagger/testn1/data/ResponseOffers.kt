package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName

data class ResponseOffers(
    @SerializedName("result") val result: List<Offer>,
    @SerializedName("metadata") val metadata: Metadata
)