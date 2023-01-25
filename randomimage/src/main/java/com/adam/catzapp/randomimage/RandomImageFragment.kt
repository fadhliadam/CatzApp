package com.adam.catzapp.randomimage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adam.catzapp.randomimage.databinding.FragmentRandomImageBinding
import com.adam.catzapp.randomimage.di.randomImageModule
import com.adam.core.data.Resource
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class RandomImageFragment : Fragment() {
    private var _binding: FragmentRandomImageBinding? = null
    private val binding get() = _binding!!

    private val randomImageViewModel : RandomImageViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(randomImageModule)
        _binding = FragmentRandomImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity!=null){
            binding.btnRandom.setOnClickListener{
                randomImageViewModel.randomImage.observe(viewLifecycleOwner){catImage ->
                    if(catImage != null){
                        when(catImage){
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewErrorRandomimage.root.visibility = View.GONE
                                binding.ivCat.visibility = View.VISIBLE
                                Glide.with(view)
                                    .load(catImage.data?.random()?.url)
                                    .into(binding.ivCat)
                                binding.btnRandom.text = getString(R.string.find_another_cat)
                            }
                            is Resource.Loading -> {
                                binding.ivCat.visibility = View.GONE
                                binding.viewErrorRandomimage.root.visibility = View.GONE
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.ivCat.visibility = View.GONE
                                binding.viewErrorRandomimage.root.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}