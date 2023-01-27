package com.adam.catzapp

import com.adam.core.domain.model.Breed
import com.adam.core.domain.model.Cat

object DataDummy {

    fun generateDummyListCat(): List<Cat> {
        val listCat: MutableList<Cat> = arrayListOf()
        for (i in 0..100) {
            val catItem = Cat(
                i,
                i.toString(),
                "https://test.jpg",
                i,
                i.toString(),
            )
            listCat.add(catItem)
        }
        return listCat
    }

    fun generateDummyBreed(): Breed {
        return Breed(
            "id",
            "name",
            "origin",
            "CC",
            "desc",
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            "temperament",
            1,
            1,
            1,
            1,
            false,
        )
    }

    fun generateDummyListBreed(): List<Breed> {
        val listBreed: MutableList<Breed> = arrayListOf()
        for(i in 1..100){
            val breedItem = Breed(
                "id",
                "name",
                "origin",
                "CC",
                "desc",
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                "temperament",
                1,
                1,
                1,
                1,
                false,
            )
            listBreed.add(breedItem)
        }
        return listBreed
    }
}