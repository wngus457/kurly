package com.juhyeon.kurly.shared.ui.common.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

val Int.textDp: TextUnit
    @Composable get() = with(LocalDensity.current) { (this@textDp).dp.toSp() }

val Double.textDp: TextUnit
    @Composable get() = with(LocalDensity.current) { (this@textDp).dp.toSp() }

val Dp.px: Float
    @Composable get() = LocalDensity.current.run { this@px.toPx() }

val Int.pxToDp: Dp
    @Composable get() = with(LocalDensity.current) { this@pxToDp.toDp() }