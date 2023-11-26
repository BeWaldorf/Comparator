package com.example.comparator.model

data class SearchItem(
    val id: String,
    val imageUrl: String,
    val description: String,
    val price: Double,
    var quantity: Int,
)