package com.app.bookstore.feature.dashboard

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.app.bookstore.db.BookDao
import com.app.bookstore.db.BookData
import com.app.bookstore.db.BookDatabase
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository
import com.app.bookstore.feature.dashboard.presentation.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.Executors


/**
 * Created by Nalan Ulusoy on 31,Temmuz,2022
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [29])
class DashboardViewModelTest {
    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel: DashboardViewModel
    private lateinit var  database: BookDatabase
    private lateinit var bookDao: BookDao

    private lateinit var repository: BookDashboardRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        repository= mock(BookDashboardRepository::class.java)
        viewModel = DashboardViewModel(repository)
        viewModel.favBooks = emptyList()
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = Room
            .inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .allowMainThreadQueries()
            .build()
        bookDao = database.getDao()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        database.close()
    }

    @Test
    fun getFavBooksEmptyTest() = runTest {

        launch(Dispatchers.Main) {
            viewModel.getFavBooks()
        }

        Assert.assertEquals(viewModel.favBooks.size, 0)
        verify(repository,times(1)).getFavoriteBooksData()
    }

    @Test
    fun getFavBooksNotEmptyTest() = runTest {
       /* val addBook1 = BookData("31.08.2022", "Haryy potter", "www.github.com/", "1234")
        val addBook2 = BookData("31.08.2022", "Haryy potter", "www.github.com/", "1235")
        launch(Dispatchers.Main) {
            bookDao.insert(addBook1)
            bookDao.insert(addBook2)
            //viewModel.getFavBooks()
        }
        bookDao.getFavoriteBooksData().collectLatest {
           Assert.assertEquals(it.size,2)
        }
       // Assert.assertEquals(viewModel.favBooks.size, 2)
        verify(repository,times(1)).getFavoriteBooksData()*/
    }



    @Test
    fun addFavoriteVerify() = runTest {
        //Given
        val addBook = BookData("31.08.2022", "Haryy potter", "www.github.com/", "1234")
        //When
        launch(Dispatchers.Main) {
            viewModel.addFavorite(addBook)
        }
        //Then
        verify(repository,times(1)).addFavoriteBook(addBook)
    }

    //https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/
}