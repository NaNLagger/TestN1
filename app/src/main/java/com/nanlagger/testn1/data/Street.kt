package com.nanlagger.testn1.data

import com.google.gson.annotations.SerializedName

/**
"name_seo": "Mendeleeva-prospekt",
"name_ru": "Менделеева проспект",
"name_translit": "Mendeleeva-prospekt",
"name_raw_ru": "Менделеева",
"id": 879434,
"abbr_raw_ru": "пр-кт"
 */

data class Street(
    @SerializedName("id") val id: Long,
    @SerializedName("name_seo") val nameSeo: String,
    @SerializedName("name_ru") val nameRu: String,
    @SerializedName("name_translit") val nameTranslit: String,
    @SerializedName("name_raw_ru") val nameRawRu: String,
    @SerializedName("abbr_raw_ru") val abbr_raw_ru: String
)