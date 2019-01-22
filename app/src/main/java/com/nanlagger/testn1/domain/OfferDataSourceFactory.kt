package com.nanlagger.testn1.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nanlagger.testn1.data.Offer
import com.nanlagger.testn1.data.OffersService
import io.reactivex.Scheduler

class OfferDataSourceFactory(
    private val offersService: OffersService,
    private val uiScheduler: Scheduler,
    private val ioScheduler: Scheduler
) : DataSource.Factory<Int, Offer>() {

    val sourceLiveData = MutableLiveData<OfferDataSource>()

    override fun create(): DataSource<Int, Offer> {
        val offerDataSource = OfferDataSource(offersService, uiScheduler, ioScheduler)
        sourceLiveData.postValue(offerDataSource)
        return offerDataSource
    }
}