package com.juhyeon.kurly.shared.util.kotlin.extension

import java.text.DecimalFormat

fun Int.toSalePercent(price: Int): Int = (100 - ((this.toDouble() / price.toDouble()) * 100)).toInt()

fun Int.applyCommaFormat(): String = DecimalFormat("###,###").format(this)
