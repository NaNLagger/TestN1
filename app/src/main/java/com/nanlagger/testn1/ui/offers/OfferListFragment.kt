package com.nanlagger.testn1.ui.offers

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanlagger.testn1.R
import com.nanlagger.testn1.di.offerListModule
import com.nanlagger.testn1.ui.common.BaseFragment
import kotlinx.android.synthetic.main.offer_list_fragment.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

class OfferListFragment : BaseFragment(), KodeinAware {
    private val _parentKodein by closestKodein { context!! }
    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein)
        bind() from instance(this@OfferListFragment)
        import(offerListModule)
    }
    override val layoutId: Int = R.layout.offer_list_fragment

    companion object {
        fun newInstance() = OfferListFragment()
    }

    private val viewModel: OfferListViewModel by instance()
    private val offerAdapter by lazy { OfferAdapter(OfferDiffCallback(), viewModel::openOffer) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.offers.observe(this, Observer {
            offerAdapter.submitList(it)
        })
        viewModel.isInitLoading.observe(this, Observer {
            swipeRefresh.isRefreshing = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleOffers.layoutManager = LinearLayoutManager(context)
        recycleOffers.adapter = offerAdapter

        swipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }
}
