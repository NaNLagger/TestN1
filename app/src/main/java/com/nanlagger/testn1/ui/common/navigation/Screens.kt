package com.nanlagger.testn1.ui.common.navigation

import androidx.fragment.app.Fragment
import com.nanlagger.testn1.data.Offer
import com.nanlagger.testn1.ui.offer.OfferDetailFragment
import com.nanlagger.testn1.ui.offers.OfferListFragment

class OfferListScreen : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return OfferListFragment.newInstance()
    }
}
class OfferDetailScreen(private val offer: Offer) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return OfferDetailFragment.newInstance(offer)
    }
}