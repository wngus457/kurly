package com.juhyeon.kurly.shared.ui.system.shimmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.juhyeon.kurly.shared.ui.common.extension.shimmerEffect

@Composable
fun BasicShimmer(
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(0.dp)
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .clip(shape)
            .shimmerEffect()
    )
}