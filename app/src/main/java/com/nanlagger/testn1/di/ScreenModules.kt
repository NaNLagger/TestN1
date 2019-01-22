package com.nanlagger.testn1.di

import androidx.lifecycle.ViewModelProviders
import com.nanlagger.testn1.domain.OfferDataSourceFactory
import com.nanlagger.testn1.ui.offer.OfferDetailFragment
import com.nanlagger.testn1.ui.offer.OfferDetailViewModel
import com.nanlagger.testn1.ui.offers.OfferListFragment
import com.nanlagger.testn1.ui.offers.OfferListViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val mainModule = Kodein.Module("mainModule") {

}

val offerListModule = Kodein.Module("offerListModule") {

    bind<OfferListViewModel.Factory>() with provider { OfferListViewModel.Factory(instance(), instance()) }

    bind<OfferListViewModel>() with provider {
        ViewModelProviders.of(instance<OfferListFragment>(), instance<OfferListViewModel.Factory>())
            .get(OfferListViewModel::class.java)
    }
}

val offerDetailModule = Kodein.Module("offerDetailModule") {

    bind<OfferDetailViewModel.Factory>() with provider { OfferDetailViewModel.Factory(instance()) }

    bind<OfferDetailViewModel>() with provider {
        ViewModelProviders.of(instance<OfferDetailFragment>(), instance<OfferDetailViewModel.Factory>())
            .get(OfferDetailViewModel::class.java)
    }
}