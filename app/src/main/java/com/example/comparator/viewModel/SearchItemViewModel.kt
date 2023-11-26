package com.example.comparator.viewModel

import androidx.compose.runtime.mutableStateListOf
import com.example.comparator.model.SearchItem

class SearchItemViewModel {
    private val _searchItems = mutableStateListOf<SearchItem>()
    private val searchItems: List<SearchItem> get() = _searchItems

    fun addSearchItem(searchItem: SearchItem) {
        _searchItems.add(searchItem)
    }

    fun removeSearchItem(searchItem: SearchItem) {
        _searchItems.remove(searchItem)
    }

    fun updateSearchItemQuantity(searchItem: SearchItem, newQuantity: Int) {
        val updatedSearchItem = searchItem.copy(quantity = newQuantity)
        _searchItems.remove(searchItem)
        _searchItems.add(updatedSearchItem)
    }

    fun getCartTotal(): Double {
        return searchItems.sumByDouble { it.price * it.quantity }
    }
}