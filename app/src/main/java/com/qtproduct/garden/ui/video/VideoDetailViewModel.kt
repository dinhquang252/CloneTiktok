package com.qtproduct.garden.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.qtproduct.garden.repositories.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @author quangtran
 * @since 9/15/23
 */

@UnstableApi
@HiltViewModel
class VideoDetailViewModel @Inject constructor(
    val videoPlayer: ExoPlayer,
    val videoRepository: VideoRepository
) : ViewModel() {
    private var _uiState = MutableStateFlow<VideoDetailUIState>(VideoDetailUIState.Default)
    val uiState: StateFlow<VideoDetailUIState> get() = _uiState

    init {
        videoPlayer.repeatMode = REPEAT_MODE_ALL
        videoPlayer.playWhenReady = true
        videoPlayer.prepare()
    }

    fun handleAction(action: VideoDetailAction) {
        when (action) {
            is VideoDetailAction.LoadData -> {
                val videoId = action.id
                loadVideo(videoId = videoId)
            }

            is VideoDetailAction.ToggleVideo -> {

            }

            else -> {}
        }
    }

    private fun loadVideo(videoId: Int) {
        _uiState.value = VideoDetailUIState.Loading
        viewModelScope.launch {
            delay(100)
            val video = videoRepository.getVideo()
            playVideo(video)
            _uiState.value = VideoDetailUIState.Success
        }
    }

    private fun playVideo(videoResourceId: Int) {
        val uri = RawResourceDataSource.buildRawResourceUri(videoResourceId)
        val mediaItem = MediaItem.fromUri(uri)
        videoPlayer.setMediaItem(mediaItem)
        videoPlayer.play()
    }
}

sealed interface VideoDetailUIState {
    object Default : VideoDetailUIState
    object Loading : VideoDetailUIState
    object Success : VideoDetailUIState
    data class Error(val msg: String) : VideoDetailUIState
}

sealed class VideoDetailAction {
    data class LoadData(val id: Int) : VideoDetailAction()
    object ToggleVideo : VideoDetailAction()
}