package com.nanlagger.testn1.ui.offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nanlagger.testn1.data.Offer
import ru.terrakok.cicerone.Router

class OfferDetailViewModel(
    private val router: Router
) : ViewModel() {

    val offer: LiveData<Offer>
        get() = offerLiveData
    private val offerLiveData = MutableLiveData<Offer>()

    fun init(offer: Offer) {
        offerLiveData.value = offer
    }

    fun back() {
        router.exit()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val router: Router) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OfferDetailViewModel(router) as T
        }
    }
}
