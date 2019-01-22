package com.nanlagger.testn1.ui.offers

import androidx.recyclerview.widget.DiffUtil
import com.nanlagger.testn1.data.Offer

class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

}