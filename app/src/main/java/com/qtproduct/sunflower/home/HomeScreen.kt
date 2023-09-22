package com.qtproduct.sunflower.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.nhatvm.toptop.R
import com.qtproduct.garden.ui.theme.ToptopTheme

/**
 * @author quangtran
 * @since 9/22/23
 */

enum class SunflowerPage(
    @StringRes val titleResId: Int,
    @DrawableRes val drawableResId: Int
) {
    MY_GARDEN(R.string.my_garden_title, R.drawable.ic_my_garden_active),
    PLANT_LIST(R.string.plant_list_title, R.drawable.ic_plant_list_active)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier) {
    val pagerState = rememberPagerState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(scrollBehavior = scrollBehavior)
        }) {
        HomePagerScreen(pageState = pagerState, modifier = modifier.padding(it))
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(title = {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displaySmall
        )
    }, modifier = modifier.statusBarsPadding(), scrollBehavior = scrollBehavior)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomePagerScreen(modifier: Modifier = Modifier, pageState: PagerState) {
    val pages = SunflowerPage.values()
    Column(modifier) {
        TabRow(selectedTabIndex = pageState.currentPage) {
            pages.forEachIndexed { index, sunflowerPage ->
                val title = stringResource(id = sunflowerPage.titleResId)
                Tab(
                    selected = pageState.currentPage == index,
                    onClick = {},
                    text = {
                        Text(text = title)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = sunflowerPage.drawableResId),
                            contentDescription = title,
                        )
                    },
                    unselectedContentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun HomeScreenPreview() {
    ToptopTheme {
        HomeScreen(
            Modifier
        )
    }
}
