package com.adam.core.utils

import com.adam.core.data.source.local.entity.BreedEntity
import com.adam.core.data.source.local.entity.CatEntity
import com.adam.core.data.source.remote.response.BreedsResponseItem
import com.adam.core.data.source.remote.response.ListCatResponseItem
import com.adam.core.domain.model.Breed
import com.adam.core.domain.model.Cat

object DataMapper {
    fun mapCatResponsesToEntities(input: List<ListCatResponseItem>): List<CatEntity>{
        val catList = ArrayList<CatEntity>()
        input.map{
            val cat = CatEntity(
                width = it.width,
                catId = it.id,
                url = it.url,
                height = it.height,
                breedsId = "0"
            )
            catList.add(cat)
        }
        return catList
    }

    fun mapBreedResponsesToEntities(input: List<BreedsResponseItem>): List<BreedEntity> {
        val breedList = ArrayList<BreedEntity>()
        input.map {
            val breed = BreedEntity(
                breedId = it.id,
                description = it.description,
                breedName = it.name,
                energyLevel = it.energyLevel,
                adaptability = it.adaptability,
                affectionLevel = it.affectionLevel,
                childFriendly = it.childFriendly,
                dogFriendly = it.dogFriendly,
                grooming = it.grooming,
                healthIssues = it.healthIssues,
                intelligence = it.intelligence,
                origin = it.origin,
                countryCode = it.countryCode,
                sheddingLevel = it.sheddingLevel,
                socialNeeds = it.socialNeeds,
                strangerFriendly = it.strangerFriendly,
                temperament = it.temperament,
                isFavorite = false,
            )
            breedList.add(breed)
        }
        return breedList
    }

    fun mapCatEntitiesToDomain(input:  List<CatEntity>): List<Cat> =
        input.map{
            Cat(
                width = it.width,
                id = it.catId,
                url = it.url,
                height = it.height,
                breedsId = "0"
            )
        }

    fun mapBreedEntitiesToDomain(input: List<BreedEntity>): List<Breed> =
        input.map {
            Breed(
                breedId = it.breedId,
                description = it.description,
                breedName = it.breedName,
                energyLevel = it.energyLevel,
                adaptability = it.adaptability,
                affectionLevel = it.affectionLevel,
                childFriendly = it.childFriendly,
                dogFriendly = it.dogFriendly,
                grooming = it.grooming,
                healthIssues = it.healthIssues,
                intelligence = it.intelligence,
                origin = it.origin,
                countryCode = it.countryCode,
                sheddingLevel = it.sheddingLevel,
                socialNeeds = it.socialNeeds,
                strangerFriendly = it.strangerFriendly,
                temperament = it.temperament,
                isFavorite = it.isFavorite,
            )
        }

    fun mapCatDomainToEntity(input: List<Cat>?) : List<CatEntity> {
        val catList = ArrayList<CatEntity>()
        input?.map{
            val cat = CatEntity(
                width = it.width,
                catId = it.id,
                url = it.url,
                height = it.height,
                breedsId = "0"
            )
            catList.add(cat)
        }
        return catList
    }

    fun mapBreedDomainToEntity(input: Breed) = BreedEntity(
        breedId = input.breedId,
        description = input.description,
        breedName = input.breedName,
        energyLevel = input.energyLevel,
        adaptability = input.adaptability,
        affectionLevel = input.affectionLevel,
        childFriendly = input.childFriendly,
        dogFriendly = input.dogFriendly,
        grooming = input.grooming,
        healthIssues = input.healthIssues,
        intelligence = input.intelligence,
        origin = input.origin,
        countryCode = input.countryCode,
        sheddingLevel = input.sheddingLevel,
        socialNeeds = input.socialNeeds,
        strangerFriendly = input.strangerFriendly,
        temperament = input.temperament,
        isFavorite = input.isFavorite
    )
}