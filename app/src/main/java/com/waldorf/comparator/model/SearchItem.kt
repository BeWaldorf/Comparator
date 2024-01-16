package com.waldorf.comparator.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class SearchItem(
    val name: String,
    var price: String? = "€?,??",
    @SerialName(value = "image_link")
    val imageLink: String,
    var link: String? = null,
    @SerialName(value = "delivery_price")
    val deliveryPrice: String? = null,
    @Transient
    var cc: String? = null
)