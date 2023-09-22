package com.qtproduct.garden.ui.following

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import com.nhatvm.toptop.R
import com.qtproduct.garden.ui.designsystem.TopTopVideoPlayer
import com.qtproduct.garden.ui.theme.ToptopTheme
import kotlin.math.absoluteValue

/**
 * @author quangtran
 * @since 9/18/23
 */
@OptIn(ExperimentalFoundationApi::class)
@UnstableApi
@Composable
fun FollowingScreen() {
    val pagerState = rememberPagerState()

    val cardWidth = (LocalConfiguration.current.screenWidthDp * 2 / 3) - 24


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.6f),
            pageCount = 10,
            state = pagerState,
            pageSize = PageSize.Fixed(cardWidth.dp),
            pageSpacing = 12.dp,
            contentPadding = PaddingValues(24.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .width(cardWidth.dp)
                    .aspectRatio(0.6f)
                    .graphicsLayer {
                        val pagerOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                        scaleY = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                        )
                    }.clip(RoundedCornerShape(16.dp))
            ) {
                CreateCard(name = "Donal Trump", nickName = "Trump", onFollow = { /*TODO*/ }) {

                }
            }
        }
    }
}

@UnstableApi
@Composable
fun CreateCard(
    modifier: Modifier = Modifier,
    name: String,
    nickName: String,
    onFollow: () -> Unit,
    onClose: () -> Unit
) {
    val videoPlayer = ExoPlayer.Builder(LocalContext.current).build()
    videoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    videoPlayer.playWhenReady = true
    videoPlayer.prepare()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
    ) {
        val (videoIntro, btnClose, imgAvatar, tvName, tvNickName, btnFollow) = createRefs()

        TopTopVideoPlayer(player = videoPlayer, modifier = Modifier.constrainAs(videoIntro) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })

        IconButton(onClick = onClose, modifier = modifier.constrainAs(btnClose) {
            top.linkTo(parent.top, margin = 12.dp)
            end.linkTo(parent.end, margin = 12.dp)
        }) {
            Icon(Icons.Sharp.Close, tint = Color.White, contentDescription = "Close icon")
        }

        Button(onClick = onFollow, modifier = Modifier
            .constrainAs(btnFollow) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, margin = 24.dp)
            }
            .padding(
                horizontal = 48.dp, vertical = 12.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFE94359),
                contentColor = Color.White
            )) {
            Text(text = "Follow", style = MaterialTheme.typography.body1.copy(color = Color.White))
        }
        Text(
            text = nickName,
            style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray),
            modifier = Modifier.constrainAs(tvNickName) {
                bottom.linkTo(btnFollow.top, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Text(
            text = name,
            style = MaterialTheme.typography.body1.copy(color = Color.White),
            modifier = Modifier.constrainAs(tvName) {
                bottom.linkTo(tvNickName.top, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        AvatarFollowing(modifier = Modifier.constrainAs(imgAvatar) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(tvName.top, margin = 8.dp)
        })
    }

    val uri = RawResourceDataSource.buildRawResourceUri(R.raw.test)
    val mediaItem = MediaItem.fromUri(uri)
    videoPlayer.setMediaItem(mediaItem)
    SideEffect {
        videoPlayer.play()
    }
}

@Composable
fun AvatarFollowing(modifier: Modifier = Modifier) {
    val avatarWidth = LocalConfiguration.current.screenWidthDp * 0.2
    Image(
        painter = painterResource(id = R.drawable.ic_dog),
        contentDescription = "avatar",
        modifier = Modifier
            .size(avatarWidth.dp)
            .background(color = Color.White, shape = CircleShape)
            .border(
                color = Color.White, width = 2.dp, shape = CircleShape
            )
            .clip(CircleShape)
    )
}

@Preview
@Composable
@UnstableApi
fun AvatarFollowingPreview() {
    ToptopTheme {
        FollowingScreen()
    }
}