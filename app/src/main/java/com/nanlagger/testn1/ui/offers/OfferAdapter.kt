package com.nanlagger.testn1.ui.offers

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.nanlagger.testn1.R
import com.nanlagger.testn1.data.Offer
import com.nanlagger.testn1.tools.inflate
import com.nanlagger.testn1.tools.visible
import com.nanlagger.testn1.ui.common.ImagePagerAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.offer_item_layout.*
import java.text.NumberFormat
import java.util.*

class OfferAdapter(
    offerDiffCallback: OfferDiffCallback,
    private val clickListener: (Offer) -> Unit = {}
) : PagedListAdapter<Offer, OfferAdapter.OfferViewHolder>(offerDiffCallback) {

    private val numberFormatter = NumberFormat.getInstance(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(parent.inflate(R.layout.offer_item_layout))
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val offer = getItem(position)
        if (offer != null) {
            holder.bind(offer)
        } else {
            holder.bindEmpty()
        }
    }

    inner class OfferViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        private val resources = itemView.resources

        fun bind(offer: Offer) {
            itemView.setOnClickListener { clickListener(offer) }

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
        }

        fun bindEmpty() {

        }

        private fun bindArea(view: TextView, resourceId: Int, value: Int?) {
            view.visible(value != null)
            if (value != null) {
                view.text = resources.getString(resourceId, value / 100)
            }
        }
    }
}