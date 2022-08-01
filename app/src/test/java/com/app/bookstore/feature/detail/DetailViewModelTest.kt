package com.app.bookstore.feature.dashboard

import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import com.app.bookstore.feature.detail.presentation.DetailViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Nalan Ulusoy on 01,Ağustos,2022
 */
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    lateinit var viewModel: DetailViewModel

    @Mock
    lateinit var repository: VolumeDetailRepository

    @Before
    fun setUp() {

        viewModel = DetailViewModel(repository)
    }

    @Test
    fun fetchVolumeIdByIdTest() {
        //Given
        val id = "1234"
        //When
        viewModel.fetchVolumeIdById(id)

        //Then
        Assert.assertNotNull(viewModel.volumeId)
        Assert.assertNotEquals(id, viewModel.volumeId)
    }

    @Test
    fun getDetailDataNotNullTest() {

        val data = viewModel.detailData

        Assert.assertNotNull(data)
    }
}