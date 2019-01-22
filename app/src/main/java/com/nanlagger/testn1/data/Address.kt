package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Address(
    @SerializedName("street") val street: Street,
    @SerializedName("house_number") val houseNumber : String
) : Serializable