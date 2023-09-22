package com.qtproduct.garden.ui.foryou

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.UnstableApi
import com.qtproduct.garden.ui.video.VideoDetailScreen
import com.qtproduct.garden.ui.video.VideoDetailViewModel

/**
 * @author quangtran
 * @since 9/18/23
 */
@UnstableApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListForUVideoScreen(
    modifier: Modifier = Modifier
) {
    VerticalPager(pageCount = 10) { videoId ->
        val viewModel: VideoDetailViewModel = hiltViewModel(key = videoId.toString())
        VideoDetailScreen(videoId = videoId, viewModel = viewModel)
    }
}