package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("resultset") val resultSet: ResultSet
)