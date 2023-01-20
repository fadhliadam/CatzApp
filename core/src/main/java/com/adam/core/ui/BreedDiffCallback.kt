package com.adam.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.adam.core.domain.model.Breed

class BreedDiffCallback(private val mOldBreedList: List<Breed>, private val mNewBreedList: List<Breed>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldBreedList.size
    }
    override fun getNewListSize(): Int {
        return mNewBreedList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldBreedList[oldItemPosition].breedId == mNewBreedList[newItemPosition].breedId
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBreed = mOldBreedList[oldItemPosition]
        val newBreed = mNewBreedList[newItemPosition]
        return oldBreed.breedName == newBreed.breedName
    }
}