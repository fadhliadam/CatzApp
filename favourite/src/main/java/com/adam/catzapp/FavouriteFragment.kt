package com.adam.catzapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adam.catzapp.di.favouriteModule
import com.adam.catzapp.favourite.databinding.FragmentFavouriteBinding
import com.adam.catzapp.ui.detail.DetailBreedActivity
import com.adam.core.ui.BreedsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavouriteFragment : Fragment() {
    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private val favouriteViewModel : FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favouriteModule)
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity!=null){
            val breedsAdapter = BreedsAdapter()
            breedsAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailBreedActivity::class.java)
                intent.putExtra(DetailBreedActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favouriteViewModel.favourite.observe(viewLifecycleOwner){ favCat ->
                breedsAdapter.setData(favCat)
                binding.viewEmpty.root.visibility = if(favCat.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvCat){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = breedsAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}