package com.nanlagger.testn1.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.nanlagger.testn1.data.Offer
import com.nanlagger.testn1.data.OffersService
import com.nanlagger.testn1.data.ResponseOffers
import com.nanlagger.testn1.tools.addTo
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class OfferDataSource(
    private val offersService: OffersService,
    private val uiScheduler: Scheduler,
    private val ioScheduler: Scheduler
) : PositionalDataSource<Offer>() {

    val isInitLoading: LiveData<Boolean>
        get() = isInitLoadingLiveData

    val isRangeLoading: LiveData<Boolean>
        get() = isRangeLoadingLiveData

    private val compositeDisposable = CompositeDisposable()
    private val isInitLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    private val isRangeLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Offer>) {
        getOffers(params.loadSize, params.startPosition)
            .doOnSubscribe { isRangeLoadingLiveData.postValue(true) }
            .subscribe({
                isRangeLoadingLiveData.postValue(false)
                callback.onResult(it.result)
            }, {
                isRangeLoadingLiveData.postValue(false)
                Timber.e(it)
            })
            .addTo(compositeDisposable)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Offer>) {
        getOffers(params.requestedLoadSize, params.requestedStartPosition)
            .doOnSubscribe { isInitLoadingLiveData.postValue(true) }
            .subscribe({
                isInitLoadingLiveData.postValue(false)
                callback.onResult(it.result, 0, it.metadata.resultSet.count)
            }, {
                isInitLoadingLiveData.postValue(false)
                Timber.e(it)
            })
            .addTo(compositeDisposable)
    }

    fun clear() {
        compositeDisposable.dispose()
    }

    private fun getOffers(limit: Int, offset: Int): Single<ResponseOffers> {
        return offersService.getOffers(limit, offset)
            .observeOn(uiScheduler)
            .subscribeOn(ioScheduler)
    }
}