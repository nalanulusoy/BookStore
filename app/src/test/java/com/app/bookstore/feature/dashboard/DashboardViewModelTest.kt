package com.app.bookstore.feature.dashboard

import com.app.bookstore.db.BookData
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository
import com.app.bookstore.feature.dashboard.presentation.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Nalan Ulusoy on 31,Temmuz,2022
 */
@RunWith(MockitoJUnitRunner::class)
class DashboardViewModelTest {
    val scope = TestScope()
    private lateinit var viewModel: DashboardViewModel

    @Mock
    private lateinit var repository: BookDashboardRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(StandardTestDispatcher(scope.testScheduler))
        viewModel = DashboardViewModel(repository)
        viewModel.favBooks = emptyList()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getFavBooksTest() = scope.runTest {
        launch {
            viewModel.getFavBooks()
        }

        Assert.assertNotEquals(viewModel.favBooks.size, 0)

    }


    @Test
    fun addFavoriteTest() = runTest(UnconfinedTestDispatcher()) {
        val addBook = BookData("31.08.2022", "Haryy potter", "www.github.com/", "1234")

        launch {
            viewModel.addFavorite(addBook)
        }
        verify(repository).addFavoriteBook(addBook)
    }
}