package com.juhyeon.kurly.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juhyeon.kurly.shared.ui.system.shimmer.BasicShimmer

@Composable
internal fun HomeShimmerComponent() {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        BasicShimmer(
            modifier = Modifier.size(250.dp, 20.dp),
            shape = RoundedCornerShape(6.dp)
        )

        LazyVerticalGrid(
            modifier = Modifier
                .heightIn(max = 600.dp)
                .wrapContentHeight(),
            columns = GridCells.Fixed(3),
            userScrollEnabled = false,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(6) {
                ProductShimmer()
            }
        }

        BasicShimmer(
            modifier = Modifier.size(200.dp, 20.dp),
            shape = RoundedCornerShape(6.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            (0..4).forEach {
                ProductShimmer()
            }
        }
    }
}

@Composable
private fun ProductShimmer() {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        BasicShimmer(
            modifier = Modifier.size(150.dp, 200.dp),
            shape = RoundedCornerShape(8.dp)
        )
        BasicShimmer(
            modifier = Modifier.size(150.dp, 14.dp),
            shape = RoundedCornerShape(8.dp)
        )
        BasicShimmer(
            modifier = Modifier.size(100.dp, 14.dp),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeShimmerComponentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        HomeShimmerComponent()
    }
}