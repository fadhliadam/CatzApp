package com.adam.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adam.core.databinding.ItemListCatsBinding
import com.adam.core.domain.model.Breed
import com.murgupluoglu.flagkit.FlagKit

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.ListViewHolder>() {
    private val listData = ArrayList<Breed>()

    var onItemClick: ((Breed) -> Unit)? = null

    inner class ListViewHolder(private var binding: ItemListCatsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Breed) {
            val resourceId = FlagKit.getResId(binding.root.context, data.countryCode)
            binding.ivItemImage.setImageResource(resourceId)
            binding.tvItemName.text = data.breedName
            binding.tvItemTemperament.text = data.temperament
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }

    fun setData(newListData: List<Breed>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListCatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size
}