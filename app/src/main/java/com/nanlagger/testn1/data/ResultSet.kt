package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName

data class ResultSet(
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("count") val count: Int
)