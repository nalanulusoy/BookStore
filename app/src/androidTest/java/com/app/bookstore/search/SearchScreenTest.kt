package com.app.bookstore.search

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.app.bookstore.feature.dashboard.data.response.BookResult
import com.app.bookstore.feature.dashboard.data.response.VolumeInfo
import com.app.bookstore.feature.search.domain.BookSearchRepository
import com.app.bookstore.feature.search.presentation.BookListItem
import com.app.bookstore.feature.search.presentation.SearchViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


/**
 * Created by Nalan Ulusoy on 31,Temmuz,2022
 */
@HiltAndroidTest
class SearchScreenTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var navController: TestNavHostController

    lateinit var viewModel: SearchViewModel

    @Before
    fun init() {
        val repository = Mockito.mock(BookSearchRepository::class.java)
        viewModel = SearchViewModel(repository)
        hiltRule.inject()
    }

    @Test
    fun bookListItemTest() {
        val filteredBook = BookResult(
            "123",
            "Mystery",
            null,
            VolumeInfo("Hary Potter", null, "", null, null, null, null, null, null, ""),
            null
        )
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            BookListItem(
                bookText = filteredBook.volumeInfo?.title.orEmpty(),
                onItemClick = { selectedBoook ->
                    //navController.navigate(NavScreen.Detail.argDetailScreen + filteredBook.id)
                })
        }
        composeTestRule.onNodeWithText(filteredBook.volumeInfo?.title.orEmpty()).performClick()
            .assertIsDisplayed()
    }
}