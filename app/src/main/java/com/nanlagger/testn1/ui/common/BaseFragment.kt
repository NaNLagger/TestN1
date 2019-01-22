package com.nanlagger.testn1.ui.common

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(layoutId, container, false)!!

    protected fun showSnackMessage(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        view?.let {
            val snackbar = Snackbar.make(it, message, duration)
            snackbar.show()
        }
    }
}