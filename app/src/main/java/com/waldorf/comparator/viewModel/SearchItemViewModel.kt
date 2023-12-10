package com.waldorf.comparator.viewModel

import androidx.compose.runtime.mutableStateListOf
import com.waldorf.comparator.model.SearchItem

class SearchItemViewModel {

    private var _searchItem: SearchItem? = null
    val searchItem: SearchItem? get() = _searchItem

    constructor(testFlag: Int? = null) {
        if(testFlag != null){
            this._searchItem = SearchItem(1, "DoodleNoodle", "https://fastly.picsum.photos/id/910/536/354.jpg?hmac=tLOqOtDz3JUSQJfmxCuOdD54ipTd47kjh5FTgR2gCwQ","€69,69")
        }
    }


    fun setSearchItem(searchItem: SearchItem) {
        _searchItem = searchItem
    }

    fun createSearchItemFromAPI(){

    }

    fun getImageUrl(): String? {
        return searchItem?.imageUrl
    }

    fun getTitleString(): String? {
        return searchItem?.title
    }

    fun getFullPriceString(): String? {
        return searchItem?.fullPriceString
    }
}