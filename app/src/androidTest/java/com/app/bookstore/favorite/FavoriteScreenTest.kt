package com.app.bookstore.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.app.bookstore.db.BookData
import com.app.bookstore.feature.favorite.domain.FavoriteBookDeleteRepository
import com.app.bookstore.feature.favorite.presentation.FavoriteViewModel
import com.app.bookstore.feature.favorite.presentation.swipeCardList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


/**
 * Created by Nalan Ulusoy on 29,Temmuz,2022
 */

@HiltAndroidTest
class FavoriteScreenTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var viewModel: FavoriteViewModel

    @Before
    fun init() {
        val repository = mock(FavoriteBookDeleteRepository::class.java)
        viewModel = FavoriteViewModel(repository)
        hiltRule.inject()
    }

    @OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
    @Test
    fun swipeCardListTest() {
        val mockBookList = listOf(
            BookData("31.08.2022", "Harry Potter1", "www.github.com/", "1234"),
            BookData("31.08.2023", "Harry Potter2", "www.github.com/", "1235")
        )
        composeTestRule.setContent {
            swipeCardList(books = mockBookList, viewModel)
        }

        composeTestRule.onNodeWithText(mockBookList[0].title.orEmpty()).assertExists()
        composeTestRule.onNodeWithText(mockBookList[1].title.orEmpty()).assertExists()

        getSwipeCardItemTest(mockBookList[0].id)
        getSwipeCardItemTest(mockBookList[1].id)
    }

    private fun getSwipeCardItemTest(tagSwipeItem: String) {
        composeTestRule.onNode(hasTestTag(tagSwipeItem)).onChildren()[1].performScrollTo()
            .assertIsDisplayed()
        composeTestRule.onNode(hasTestTag(tagSwipeItem)).onChildren()[1].performScrollTo()
            .performTouchInput {
                swipeLeft()
            }
    }

}