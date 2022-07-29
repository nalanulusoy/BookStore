package com.app.bookstore.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.app.bookstore.base.extention.BookBuyLink
import com.app.bookstore.base.extention.BookPdfLink
import com.app.bookstore.feature.detail.domain.VolumeDetailRepository
import com.app.bookstore.feature.detail.presentation.BookPrice
import com.app.bookstore.feature.detail.presentation.DetailViewModel
import com.app.bookstore.feature.detail.presentation.LinkView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


/**
 * Created by Nalan Ulusoy on 29,Temmuz,2022
 */
class DetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    private lateinit var viewModel: DetailViewModel


    @Before
    fun setUp(){
        val repository = Mockito.mock(VolumeDetailRepository::class.java)
        viewModel = DetailViewModel(repository)
    }

    @Test
    fun bookPriceTest(){
        val price = "45 TL"
        composeTestRule.setContent {
            BookPrice(price = price)
        }
        composeTestRule.onNodeWithText(price).assertIsDisplayed()
        composeTestRule.onNodeWithText(price).assertExists()
    }

    @Test
    fun linkViewTest_BookPdfLink(){
        composeTestRule.setContent {
            LinkView(buyLink = "https://github.com/", previewPdf ="https://stackoverflow.com/" )
        }
        composeTestRule.onNodeWithText(BookPdfLink).assertIsDisplayed()
        composeTestRule.onNodeWithText(BookPdfLink).performClick()
    }
    @Test
    fun linkViewTest_BookBuyLink(){
        composeTestRule.setContent {
            LinkView(buyLink = "https://github.com/", previewPdf ="https://stackoverflow.com/" )
        }
        composeTestRule.onNodeWithText(BookBuyLink).assertIsDisplayed()
        composeTestRule.onNodeWithText(BookBuyLink).performClick()
    }
}