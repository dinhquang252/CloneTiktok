package com.nhatvm.toptop.video.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nhatvm.toptop.R

/**
 * @author quangtran
 * @since 9/15/23
 */
@Composable
fun AvatarView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ConstraintLayout(modifier = modifier.clickable {
        onClick()
    }) {
        val (imgAvatar, addIcon) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_dog), contentDescription = "icon avatar",
            modifier = Modifier
                .size(48.dp)
                .background(shape = CircleShape, color = Color.White)
                .clip(shape = CircleShape)
                .constrainAs(imgAvatar) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color = MaterialTheme.colors.error, shape = CircleShape)
                .constrainAs(addIcon){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Add, contentDescription = "icon add", tint = Color.White, modifier = Modifier.size(24.dp))
        }
    }
}

@Preview
@Composable
private fun PreviewScreen(){
    AvatarView{

    }
}