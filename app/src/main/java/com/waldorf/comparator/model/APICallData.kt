package com.waldorf.comparator.model

import kotlinx.serialization.Serializable

@Serializable
data class APICallData (
    var searchString: String?,
    val regions: Array<String>  = arrayOf("BE", "NL", "DE", "FR","JP", "COM")
)