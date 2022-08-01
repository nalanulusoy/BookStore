package com.app.bookstore.feature.dashboard

import com.app.bookstore.db.BookData
import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import com.app.bookstore.feature.detail.presentation.DetailViewModel
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository
import com.app.bookstore.feature.favorite.presentation.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Nalan Ulusoy on 01,Ağustos,2022
 */
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @Mock
    lateinit var repository: FavoriteBookDeleteRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun deleteFavBookVerify() = runTest {
        //Given
        val deleteBook = BookData("31.08.2022", "Haryy potter", "www.github.com/", "1234")
        //When
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
            viewModel.deleteFavBook(deleteBook)
        }
        //Then
        verify(repository,times(1)).favDeleteBook(deleteBook)
    }
}