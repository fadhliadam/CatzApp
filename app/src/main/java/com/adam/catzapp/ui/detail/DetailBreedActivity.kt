package com.adam.catzapp.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.adam.catzapp.R
import com.adam.catzapp.databinding.ActivityDetailBreedBinding
import com.adam.core.data.Resource
import com.adam.core.domain.model.Breed
import com.adam.core.ui.CatImageAdapter
import com.murgupluoglu.flagkit.FlagKit
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailBreedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBreedBinding
    private lateinit var catImageAdapter: CatImageAdapter
    private val detailBreedViewModel: DetailBreedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catImageAdapter = CatImageAdapter()
        supportActionBar?.hide()

        binding = ActivityDetailBreedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailCat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DATA, Breed::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_DATA)
        }

        showDetailCat(detailCat)
    }

    private fun showDetailCat(detailCat: Breed?){
        detailCat?.let{
            with(binding){
                detailBreedViewModel.cat(detailCat.breedId).observe(this@DetailBreedActivity){
                    cat ->
                    if(cat!=null){
                        when(cat){
                            is Resource.Loading-> loadingLayout.visibility = View.VISIBLE
                            is Resource.Success-> {
                                detailBreedViewModel.setCatBreed(cat.data, detailCat.breedId)
                                loadingLayout.visibility = View.GONE
                                catImageAdapter.setData(cat.data)
                            }
                            is Resource.Error ->{
                                progressBar.visibility = View.GONE
                                viewError.root.visibility = View.VISIBLE
                            }
                        }
                    }
                }
                with(detailCat){
                    tvDescription.text = description
                    tvBreedName.text = breedName
                    tvOrigin.text = origin
                    ratingBarAdapt.rating = adaptability.toFloat()
                    ratingBarAffection.rating = affectionLevel.toFloat()
                    ratingBarChild.rating = childFriendly.toFloat()
                    ratingBarDog.rating = dogFriendly.toFloat()
                    ratingBarEnergy.rating = energyLevel.toFloat()
                    ratingBarGrooming.rating = grooming.toFloat()
                    ratingBarHealth.rating = healthIssues.toFloat()
                    ratingBarIntelligence.rating = intelligence.toFloat()
                    ratingBarShedding.rating = sheddingLevel.toFloat()
                    ratingBarSocial.rating = socialNeeds.toFloat()
                    ratingBarStranger.rating = strangerFriendly.toFloat()
                    val resourceId = FlagKit.getResId(binding.root.context, countryCode)
                    tvOrigin.setCompoundDrawablesWithIntrinsicBounds(resourceId,0,0,0)

                    var statusFavorite = isFavorite
                    setStatusFavorite(statusFavorite)
                    binding.fabFavourite.setOnClickListener {
                        statusFavorite = !statusFavorite
                        detailBreedViewModel.setFavouriteCat(this, statusFavorite)
                        setStatusFavorite(statusFavorite)
                    }
                }

                with(rvCatImage){
                    layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    setHasFixedSize(true)
                    adapter = catImageAdapter
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavourite.setImageDrawable(ContextCompat.getDrawable
                (this, R.drawable.baseline_favorite_24_red ))
            binding.fabFavourite.supportImageTintList =
                ContextCompat.getColorStateList(this, R.color.pink)
        } else {
            binding.fabFavourite.setImageDrawable(ContextCompat.getDrawable
                (this, R.drawable.baseline_favorite_border_24_white))
            binding.fabFavourite.supportImageTintList =
                ContextCompat.getColorStateList(this, R.color.white)
        }
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}