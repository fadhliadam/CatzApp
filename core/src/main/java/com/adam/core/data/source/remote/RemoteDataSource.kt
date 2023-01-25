package com.adam.core.data.source.remote

import android.util.Log
import com.adam.core.BuildConfig
import com.adam.core.data.source.remote.network.ApiResponse
import com.adam.core.data.source.remote.network.ApiService
import com.adam.core.data.source.remote.response.BreedsResponseItem
import com.adam.core.data.source.remote.response.ListCatResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    private val apiKey = BuildConfig.APIKEY

    suspend fun getBreeds(): Flow<ApiResponse<List<BreedsResponseItem>>> {
        return flow {
            try {
                val response = apiService.getBreeds()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCatImage(breedsId: String): Flow<ApiResponse<List<ListCatResponseItem>>>{
        return flow{
            try{
                val response = apiService.getCatImage(apiKey,10,breedsId)
                if(response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRandomCatImage(): Flow<ApiResponse<List<ListCatResponseItem>>>{
        return flow{
            try{
                val response = apiService.getRandomCatImage()
                if(response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}