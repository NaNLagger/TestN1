package com.nanlagger.testn1.tools

import androidx.recyclerview.widget.DiffUtil

abstract class ListDiffCallback<out T>(val oldList: List<T>, val newList: List<T>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}