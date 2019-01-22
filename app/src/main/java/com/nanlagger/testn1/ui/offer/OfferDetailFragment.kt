package com.nanlagger.testn1.ui.offer

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.nanlagger.testn1.R
import com.nanlagger.testn1.data.Offer
import com.nanlagger.testn1.di.offerDetailModule
import com.nanlagger.testn1.tools.visible
import com.nanlagger.testn1.ui.common.BaseFragment
import com.nanlagger.testn1.ui.common.ImagePagerAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

import kotlinx.android.synthetic.main.offer_detail_fragment.*
import java.text.NumberFormat
import java.util.*

class OfferDetailFragment : BaseFragment(), KodeinAware {
    override val layoutId: Int = R.layout.offer_detail_fragment

    private val _parentKodein by closestKodein { context!! }
    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein)
        bind() from instance(this@OfferDetailFragment)
        import(offerDetailModule)
    }

    companion object {
        private const val KEY_OFFER = "key.offer"

        fun newInstance(offer: Offer): OfferDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_OFFER, offer)
            return OfferDetailFragment().apply { this.arguments = args }
        }
    }

    private val viewModel: OfferDetailViewModel by instance()
    private val numberFormatter = NumberFormat.getInstance(Locale.getDefault())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.init(arguments?.getSerializable(KEY_OFFER) as Offer)
        viewModel.offer.observe(this, Observer {
            bindOffer(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { viewModel.back() }
    }

    private fun bindOffer(offer: Offer) {
        textPrice.text = resources.getString(R.string.price, numberFormatter.format(offer.params.price))

        val address = offer.params.address?.firstOrNull()
        val street = address?.street?.nameRu
        val houseNumber = address?.houseNumber
        textStreet.text = resources.getString(R.string.address, street, houseNumber)

        val floorIsVisible = offer.params.floor != null && offer.params.floorsCount != null
        textFloor.visible(floorIsVisible)
        if (floorIsVisible) {
            textFloor.text =
                    resources.getString(R.string.floor, offer.params.floor, offer.params.floorsCount)
        }

        bindArea(textKitchenArea, R.string.kitchenArea, offer.params.kitchenArea)
        bindArea(textTotalArea, R.string.totalArea, offer.params.totalArea)
        bindArea(textLivingArea, R.string.livingArea, offer.params.livingArea)

        imageStubView.visible(offer.photos.isEmpty())
        if (offer.photos.isNotEmpty()) {
            pagerPhoto.clearOnPageChangeListeners()
            pagerPhoto.adapter = ImagePagerAdapter(offer.photos.map { it.preview })
            pagerPhoto.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    textImageCounter.text = resources.getString(R.string.photoCounter, position + 1, offer.photos.size)
                }
            })
            textImageCounter.text = resources.getString(R.string.photoCounter, pagerPhoto.currentItem + 1, offer.photos.size)
        }
        toolbar.title = resources.getString(R.string.address, street, houseNumber)
    }

    private fun bindArea(view: TextView, resourceId: Int, value: Int?) {
        view.visible(value != null)
        if (value != null) {
            view.text = resources.getString(resourceId, value / 100)
        }
    }
}
