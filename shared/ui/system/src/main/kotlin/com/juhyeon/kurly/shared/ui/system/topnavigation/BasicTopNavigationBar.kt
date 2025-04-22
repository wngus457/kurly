package com.juhyeon.kurly.shared.ui.system.topnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.juhyeon.kurly.shared.ui.system.theme.PrimaryColor
import com.juhyeon.kurly.shared.ui.system.theme.White100
import com.juhyeon.kurly.shared.ui.system.theme.bold

@Composable
fun BasicTopNavigationBar(
    modifier: Modifier = Modifier,
    title: TopNavigationTitle = TopNavigationTitle.Off
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .height(48.dp)
            .background(PrimaryColor)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.CenterStart),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (title) {
                is TopNavigationTitle.On -> {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = title.title,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = White100,
                        style = MaterialTheme.typography.bold(16)
                    )
                }
                is TopNavigationTitle.Off -> { }
            }
        }
    }
}