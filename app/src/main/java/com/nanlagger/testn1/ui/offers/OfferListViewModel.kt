package com.nanlagger.testn1.ui.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nanlagger.testn1.data.Offer
import com.nanlagger.testn1.domain.OfferDataSourceFactory
import com.nanlagger.testn1.ui.common.navigation.OfferDetailScreen
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router

class OfferListViewModel(
    private val router: Router,
    private val offersDataSourceFactory: OfferDataSourceFactory
) : ViewModel() {

    val offers: LiveData<PagedList<Offer>> = LivePagedListBuilder(offersDataSourceFactory, 20).build()
    val isInitLoading: LiveData<Boolean>
        get() = Transformations.switchMap(offersDataSourceFactory.sourceLiveData) { it.isInitLoading }
    val isRangeLoading: LiveData<Boolean>
        get() = Transformations.switchMap(offersDataSourceFactory.sourceLiveData) { it.isRangeLoading }
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun openOffer(offer: Offer) {
        router.navigateTo(OfferDetailScreen(offer))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        offersDataSourceFactory.sourceLiveData.value?.clear()
    }

    fun refresh() {
        offersDataSourceFactory.sourceLiveData.value?.invalidate()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val router: Router, private val offersDataSourceFactory: OfferDataSourceFactory) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OfferListViewModel(router, offersDataSourceFactory) as T
        }
    }
}
