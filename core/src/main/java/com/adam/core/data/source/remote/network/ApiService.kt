package com.adam.core.data.source.remote.network

import com.adam.core.data.source.remote.response.BreedsResponseItem
import com.adam.core.data.source.remote.response.ListCatResponseItem
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("breeds/")
    suspend fun getBreeds(
    ): List<BreedsResponseItem>

    @GET("images/search")
    suspend fun getCatImage(
        @Header("x-api-key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("breed_ids") breedsId: String,
    ): List<ListCatResponseItem>

    @GET("images/search")
    suspend fun getRandomCatImage(): List<ListCatResponseItem>
}