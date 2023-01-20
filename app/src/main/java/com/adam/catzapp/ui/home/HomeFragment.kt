package com.adam.catzapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adam.catzapp.databinding.FragmentHomeBinding
import com.adam.catzapp.ui.detail.DetailBreedActivity
import com.adam.core.data.Resource
import com.adam.core.ui.BreedsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val breedsAdapter = BreedsAdapter()
            breedsAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailBreedActivity::class.java)
                intent.putExtra(DetailBreedActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.breed.observe(viewLifecycleOwner){ breed ->
                if(breed != null){
                    when(breed){
                        is Resource.Loading ->  {
                            binding.ivBlank.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.VISIBLE
                            binding.viewError.root.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            binding.ivBlank.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.GONE
                            breedsAdapter.setData(breed.data)
                        }
                        is Resource.Error ->{
                            binding.ivBlank.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                        }
                    }
                }
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