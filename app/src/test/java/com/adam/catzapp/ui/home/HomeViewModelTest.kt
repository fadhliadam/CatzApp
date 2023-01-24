package com.adam.catzapp.ui.home

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
class HomeViewModelTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRuler = MainDispatcherRuler()

    @Mock private lateinit var catRepository: CatRepository

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var catUseCase: CatUseCase
    private var dummyListBreed = DataDummy.generateDummyListBreed()

    @Before
    fun setup(){
        catUseCase = CatInteractor(catRepository)
        homeViewModel = HomeViewModel(catUseCase)
    }

    @Test
    fun `when Get All Breed Should Not Null`() = runTest {
        `when`(catUseCase.getAllBreed())
            .thenReturn(flowOf(Resource.Success(dummyListBreed)))

        val actualListBreed = homeViewModel.breed.getOrAwaitValue()
        Mockito.verify(catRepository).getAllBreed()
        assertNotNull(actualListBreed)
        assertTrue(actualListBreed is Resource.Success)
        assertEquals(dummyListBreed, (actualListBreed as Resource.Success).data)
    }
}