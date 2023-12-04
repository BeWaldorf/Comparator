package com.waldorf.comparator.model

import kotlinx.serialization.*

@Serializable
data class APIResponseData (
    val priceWhole: String,
    val priceFraction: String,
    val title: String,
    val link: String = "https://www.amazon.com.be/",
    val priceSymbol: String = "€",
)