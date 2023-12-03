package com.example.comparator.model

import kotlinx.serialization.Serializable

@Serializable
data class APICallData (
    val keywords: String,
    val regions: Array<String>  = arrayOf("BE", "NL", "DE", "FR"),
    val category: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as APICallData

        if (keywords != other.keywords) return false
        if (!regions.contentEquals(other.regions)) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = keywords.hashCode()
        result = 31 * result + regions.contentHashCode()
        result = 31 * result + (category?.hashCode() ?: 0)
        return result
    }
}