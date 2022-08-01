package com.app.bookstore.feature.dashboard

import androidx.paging.compose.collectAsLazyPagingItems
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository
import com.app.bookstore.feature.favorite.presentation.FavoriteViewModel
import com.app.bookstore.feature.search.domain.BookSearchRepository
import com.app.bookstore.feature.search.presentation.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
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
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    @Mock
    lateinit var repository: BookSearchRepository
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = SearchViewModel(repository)
    }

    @Test
    fun searchStringKeyVerify(){
        val key = "Computer"
        viewModel.searchStringKey(key)

        Assert.assertNotNull(viewModel.searchKey)
        Assert.assertEquals(key,viewModel.searchKey.value)
    }
}