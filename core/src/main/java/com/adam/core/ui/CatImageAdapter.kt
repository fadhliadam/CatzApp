package com.adam.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adam.core.databinding.ItemCatImageBinding
import com.adam.core.domain.model.Cat
import com.bumptech.glide.Glide
import java.util.ArrayList

class CatImageAdapter: RecyclerView.Adapter<CatImageAdapter.ListViewHolder>() {
    private var listData = ArrayList<Cat>()

    inner class ListViewHolder(private var binding: ItemCatImageBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Cat) {
            Glide.with(itemView.context)
                .load(data.url)
                .into(binding.ivItemImage)
        }
    }

    fun setData(data: List<Cat>?){
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCatImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
}