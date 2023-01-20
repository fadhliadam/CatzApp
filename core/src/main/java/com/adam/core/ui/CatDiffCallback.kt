package com.adam.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.adam.core.domain.model.Cat

class CatDiffCallback(private val mOldCatList: List<Cat>, private val mNewCatList: List<Cat>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldCatList.size
    }
    override fun getNewListSize(): Int {
        return mNewCatList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldCatList[oldItemPosition].id == mNewCatList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldImage = mOldCatList[oldItemPosition]
        val newImage = mNewCatList[newItemPosition]
        return oldImage.url == newImage.url
    }
}