package com.adam.catzapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adam.catzapp.DataDummy
import com.adam.catzapp.MainDispatcherRuler
import com.adam.catzapp.getOrAwaitValue
import com.adam.core.data.CatRepository
import com.adam.core.data.Resource
import com.adam.core.domain.usecase.CatInteractor
import com.adam.core.domain.usecase.CatUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailBreedViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRuler = MainDispatcherRuler()

    @Mock private lateinit var catRepository: CatRepository

    private lateinit var detailBreedViewModel: DetailBreedViewModel
    private lateinit var catUseCase: CatUseCase
    private var dummyListCat = DataDummy.generateDummyListCat()
    private var dummyBreed = DataDummy.generateDummyBreed()

    @Before
    fun setup() {
        catUseCase = CatInteractor(catRepository)
        detailBreedViewModel = DetailBreedViewModel(catUseCase)
    }

    @Test
    fun `when Set Cat Breed Should Called in The Repository`() = runTest {
        detailBreedViewModel.setCatBreed(dummyListCat, dummyBreed.breedId)
        Mockito.verify(catRepository).setCatBreedId(dummyListCat, dummyBreed.breedId)
    }

    @Test
    fun `when Set Favourite Should Called In The Repository`() = runTest {
        val isFavorite = !dummyBreed.isFavorite
        detailBreedViewModel.setFavouriteCat(dummyBreed, isFavorite)
        Mockito.verify(catRepository).setFavoriteCat(dummyBreed, isFavorite)
    }

    @Test
    fun `when Get All Cat Image Should Not Null`() = runTest {
        `when`(catUseCase.getCatImageBreed(dummyBreed.breedId))
            .thenReturn(flowOf(Resource.Success(dummyListCat)))

        val actualCatImages = detailBreedViewModel.cat(dummyBreed.breedId).getOrAwaitValue()
        Mockito.verify(catRepository).getCatImageBreed(dummyBreed.breedId)
        assertNotNull(actualCatImages)
        assertTrue(actualCatImages is Resource.Success)
        assertEquals(dummyListCat, (actualCatImages as Resource.Success).data)
    }

    @Test
    fun `when Get All Cat Image Network Error Should Return Error`() = runTest {
        `when`(catUseCase.getCatImageBreed(dummyBreed.breedId))
            .thenReturn(flowOf(Resource.Error(ERROR_MSG)))

        val actualCatImages = detailBreedViewModel.cat(dummyBreed.breedId).getOrAwaitValue()
        Mockito.verify(catRepository).getCatImageBreed(dummyBreed.breedId)
        assertNotNull(actualCatImages)
        assertTrue(actualCatImages is Resource.Error)
        assertEquals(ERROR_MSG, (actualCatImages as Resource.Error).message)
    }

    private companion object {
        const val ERROR_MSG = "Error"
    }
}