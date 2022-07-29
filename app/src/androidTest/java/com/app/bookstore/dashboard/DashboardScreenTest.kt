package com.app.bookstore.dashboard

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.bookstore.feature.dashboard.data.response.BookResult
import com.app.bookstore.feature.dashboard.data.response.VolumeInfo
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository
import com.app.bookstore.feature.dashboard.presentation.*
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


/**
 * Created by Nalan Ulusoy on 24,Temmuz,2022
 */
@RunWith(AndroidJUnit4::class)
class DashboardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp(){
        val repository = mock(BookDashboardRepository::class.java)
        viewModel = DashboardViewModel(repository)
        viewModel.favBooks = emptyList()
    }

    @Test
    fun bookColumnViewTest(){
        val volumeInfo = VolumeInfo("Book", listOf(),"Book Publisher","24.08.2022","",5, listOf(),null,null,"www.google.com")

        composeTestRule.setContent {
            BookColumnView(volumeInfo = volumeInfo)
        }

        composeTestRule.onNodeWithText(volumeInfo.publishedDate.orEmpty()).assertExists()
        composeTestRule.onNodeWithText(volumeInfo.title.orEmpty()).assertExists()
    }

    @Test
    fun bookItem_IsNot_Click_Test(){
        val isTrigger = mutableStateOf(false)
        val volumeInfo = VolumeInfo("Book", listOf(),"Book Publisher","24.08.2022","",5, listOf(),null,null,"www.google.com")
        val book = BookResult("1234","kind","www.google.com",volumeInfo,null)
        composeTestRule.setContent {
            BookItem(book = book, viewModel = viewModel) {
                isTrigger.value = true
            }
        }

        assertFalse(isTrigger.value)
        composeTestRule.onNodeWithText(book.volumeInfo?.title.orEmpty()).assertIsDisplayed()
    }

    @Test
    fun bookPublishedDateTest(){
        val subTitle = "29.08.2022"
        composeTestRule.setContent {
            BookPublishedDate(subTitle)
        }
        composeTestRule.onNodeWithText(subTitle).assertIsDisplayed()
        composeTestRule.onNodeWithText(subTitle).assertExists()
    }

    @Test
    fun bookTitleTest(){
        val title = "Android Fundamentals"
        composeTestRule.setContent {
            BookTitle(title)
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(title).assertExists()
    }
}