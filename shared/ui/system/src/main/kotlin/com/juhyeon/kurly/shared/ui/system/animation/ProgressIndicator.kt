package com.juhyeon.kurly.shared.ui.system.animation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.juhyeon.kurly.shared.ui.system.theme.PrimaryColor

@Composable
fun ProgressIndicator(
    height: Dp = 40.dp,
    indicatorSize: Dp = 32.dp,
    color: Color = PrimaryColor
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .progressSemantics()
                .size(indicatorSize),
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TestProgressIndicator() {
    ProgressIndicator()
}