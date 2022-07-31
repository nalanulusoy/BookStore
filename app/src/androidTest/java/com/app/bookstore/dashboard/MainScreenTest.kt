package com.app.bookstore.dashboard

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.app.bookstore.base.NavScreen
import com.app.bookstore.feature.dashboard.domain.BookDashboardRepository
import com.app.bookstore.feature.dashboard.presentation.DashboardViewModel
import com.app.bookstore.feature.dashboard.presentation.MainActivity
import com.app.bookstore.feature.dashboard.presentation.MainScreen
import com.app.bookstore.feature.search.presentation.SearchViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


/**
 * Created by Nalan Ulusoy on 31,Temmuz,2022
 */
@HiltAndroidTest
class MainScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    private lateinit var text: String
    lateinit var navController: TestNavHostController
    lateinit var viewModel: DashboardViewModel

    @Before
    fun setUp(){
        val repository = mock(BookDashboardRepository::class.java)
        viewModel = DashboardViewModel(repository)
        hiltRule.inject()
    }

    @Test
    fun MainScreenTest() {
        val items = listOf(
            NavScreen.Dashboard,
            NavScreen.Favorite,
            NavScreen.Search
        )

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            // Sets a ComposeNavigator to the navController so it can navigate through composables
            navController.currentBackStackEntry?.destination?.route = items[0].route
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            text = stringResource(id = items[0].bottomBarData?.title ?: 0)
            MainScreen(viewModel = viewModel)
        }
        composeTestRule.onRoot().printToLog(text)
        composeTestRule.onNodeWithText(text).performClick().assertHasClickAction().assertIsDisplayed()

       Assert.assertEquals(navController.currentBackStackEntry?.destination?.route, null)
    }

 /*   @Test
    fun navigateSearchTest() {
        val items = listOf(
            NavScreen.Dashboard,
            NavScreen.Favorite,
            NavScreen.Search
        )

        composeTestRule.setContent {

            navController = TestNavHostController(LocalContext.current)
            // Sets a ComposeNavigator to the navController so it can navigate through composables
            navController.currentBackStackEntry?.destination?.route = items[2].route
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            text = stringResource(id = items[2].bottomBarData?.title ?: 0)
            MainScreen(viewModel = viewModel)
        }
        composeTestRule.onNodeWithText(text).performClick().assertHasClickAction().assertIsDisplayed()
        Assert.assertEquals(navController.currentBackStackEntry?.destination?.route, items[2].route)
    }*/

}