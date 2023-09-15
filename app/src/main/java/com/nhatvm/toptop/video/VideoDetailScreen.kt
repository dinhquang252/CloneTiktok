package com.nhatvm.toptop.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import com.nhatvm.toptop.designsystem.TopTopVideoPlayer

/**
 * @author quangtran
 * @since 9/15/23
 */
@UnstableApi
@Composable
fun VideoDetailScreen(
    videoId: Int,
    viewModel: VideoDetailViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    if (uiState.value == VideoDetailUIState.Default) {
        //loading
        viewModel.handleAction(VideoDetailAction.LoadData(videoId))
    }

    VideoDetailScreen(uiState = uiState.value, player = viewModel.videoPlayer)
}

@UnstableApi
@Composable
fun VideoDetailScreen(
    uiState: VideoDetailUIState,
    player: Player,
) {
    when (uiState) {
        is VideoDetailUIState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "loading")
            }
        }

        is VideoDetailUIState.Success -> {
            VideoDetailUIScreen(player = player)
        }

        else -> {

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@UnstableApi
@Composable
fun VideoDetailUIScreen(player: Player) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (videoPlayerView, sideBar) = createRefs()
        TopTopVideoPlayer(player = player, modifier = Modifier.constrainAs(videoPlayerView) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.matchParent
            height = Dimension.matchParent
        })
    }
}