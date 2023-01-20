package com.adam.core.data

import com.adam.core.data.source.local.LocalDataSource
import com.adam.core.data.source.remote.RemoteDataSource
import com.adam.core.data.source.remote.network.ApiResponse
import com.adam.core.data.source.remote.response.BreedsResponseItem
import com.adam.core.data.source.remote.response.ListCatResponseItem
import com.adam.core.domain.model.Breed
import com.adam.core.domain.model.Cat
import com.adam.core.domain.repository.ICatRepository
import com.adam.core.utils.AppExecutors
import com.adam.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CatRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatRepository{

    override fun getAllBreed(): Flow<Resource<List<Breed>>> =
        object : NetworkBoundResource<List<Breed>, List<BreedsResponseItem>>() {
            override fun loadFromDB(): Flow<List<Breed>> {
                return localDataSource.getAllCat().map{
                    DataMapper.mapBreedEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Breed>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<BreedsResponseItem>>> =
                remoteDataSource.getBreeds()

            override suspend fun saveCallResult(data: List<BreedsResponseItem>) {
                val breedList = DataMapper.mapBreedResponsesToEntities(data)
                localDataSource.insertBreed(breedList)
            }
        }.asFlow()

    override fun getCatImageBreed(breedsId: String): Flow<Resource<List<Cat>>> =
        object : NetworkBoundResource<List<Cat>, List<ListCatResponseItem>>(){
            override fun loadFromDB(): Flow<List<Cat>> {
                return localDataSource.getCatImageBreed(breedsId).map{
                    DataMapper.mapCatEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ListCatResponseItem>>> =
                remoteDataSource.getCatImage(breedsId)

            override suspend fun saveCallResult(data: List<ListCatResponseItem>) {
                val catImageList = DataMapper.mapCatResponsesToEntities(data)
                localDataSource.insertCat(catImageList)
            }

            override fun shouldFetch(data: List<Cat>?): Boolean = true
        }.asFlow()


    override fun getFavoriteCat(): Flow<List<Breed>> {
        return localDataSource.getFavoriteBreed().map {
            DataMapper.mapBreedEntitiesToDomain(it)
        }
    }

    override fun setCatBreedId(cat: List<Cat>?, breedsId: String) {
        val catEntity = DataMapper.mapCatDomainToEntity(cat)
        appExecutors.diskIO().execute{
            localDataSource.setCatBreedId(catEntity, breedsId)
        }
    }

    override fun setFavoriteCat(cat: Breed, state: Boolean) {
        val breedEntity = DataMapper.mapBreedDomainToEntity(cat)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteTourism(breedEntity, state)
        }
    }

}