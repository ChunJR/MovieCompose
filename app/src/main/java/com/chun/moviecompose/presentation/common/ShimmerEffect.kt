package com.chun.moviecompose.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chun.moviecompose.ui.theme.*

@Composable
fun ShimmerEffect() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(all = SMALL_PADDING),
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING),
    ) {
        items(count = 10) {
            AnimatedShimmerItem()
        }
    }
}

@Composable
fun AnimatedShimmerItem() {
    val transition = rememberInfiniteTransition(label = "")
    val alphaAnim by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500,
                easing = FastOutLinearInEasing,
            ),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "",
    )
    ShimmerItem(alpha = alphaAnim)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        color = if (isSystemInDarkTheme()) Color.Black else ShimmerLightGray,
        shape = RoundedCornerShape(size = LARGE_PADDING),
    ) {
        Column(
            modifier = Modifier.padding(all = MEDIUM_PADDING),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .size(IMAGE_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING),
                ) {}
                Spacer(modifier = Modifier.padding(all = SMALL_PADDING))
                Surface(
                    modifier = Modifier
                        .alpha(alpha = alpha)
                        .fillMaxWidth()
                        .height(ABOUT_SHIMMER_HEIGHT),
                    color = if (isSystemInDarkTheme()) ShimmerDarkGray else ShimmerMediumGray,
                    shape = RoundedCornerShape(size = SMALL_PADDING),
                ) {}
                Spacer(modifier = Modifier.padding(all = EXTRA_SMALL_PADDING))

            }
        }
    }
}

@Composable
@Preview
fun ShimmerItemPreview() {
    AnimatedShimmerItem()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ShimmerItemDarkPreview() {
    AnimatedShimmerItem()
}
