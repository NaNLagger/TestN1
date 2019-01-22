package com.nanlagger.testn1.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OffersService {

    @GET("offers/?filter[region_id]=1054&query[0][deal_type]=sell&query[0][rubric]=flats&query[0][status]=published&sort=-creation_date")
    fun getOffers(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<ResponseOffers>
}