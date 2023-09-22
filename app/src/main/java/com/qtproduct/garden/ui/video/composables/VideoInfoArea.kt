package com.qtproduct.garden.ui.video.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qtproduct.garden.ui.theme.ToptopTheme

/**
 * @author quangtran
 * @since 9/18/23
 */

@Composable
fun VideoInfoArea(
    modifier: Modifier = Modifier,
    accountName: String,
    videoName: String,
    hashTag: List<String>,
    songName: String
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = accountName,
            style = MaterialTheme.typography.h4.copy(color = Color.White),
            maxLines = 1
        )

        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = hashTag.joinToString(""),
            style = MaterialTheme.typography.body1.copy(Color.White),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = songName, style = MaterialTheme.typography.body1.copy(color = Color.White))
    }
}

@Preview
@Composable
fun VideoInfoAreaPreview() {
    ToptopTheme {
        VideoInfoArea(
            accountName = "Qiang",
            videoName = "Clone Tiktok",
            hashTag = listOf("jetpack", "android", "tiktok"),
            songName = "let go"
        )
    }
}