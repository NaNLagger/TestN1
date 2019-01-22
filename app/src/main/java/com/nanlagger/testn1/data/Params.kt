package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Params(
    @SerializedName("price") val price: Long,
    @SerializedName("rooms_count") val rooms_count: Int?,
    @SerializedName("house_addresses") val address: List<Address>?,
    @SerializedName("total_area") val totalArea: Int?,
    @SerializedName("living_area") val livingArea: Int?,
    @SerializedName("kitchen_area") val kitchenArea: Int?,
    @SerializedName("floors_count") val floorsCount: Int?,
    @SerializedName("floor") val floor: Int?
) : Serializable