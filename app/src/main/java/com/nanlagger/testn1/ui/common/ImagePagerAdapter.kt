package com.nanlagger.testn1.ui.common

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.nanlagger.testn1.R
import com.nanlagger.testn1.tools.inflate
import com.squareup.picasso.Picasso


class ImagePagerAdapter(
    private val photos: List<String?>
) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val itemView = collection.inflate(R.layout.photo_item_layout) as AppCompatImageView
        collection.addView(itemView)
        Picasso.get()
            .load(photos[position])
            .placeholder(R.mipmap.placeholder_image)
            .error(R.mipmap.placeholder_image)
            .into(itemView)
        return itemView
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return photos.size
    }
}