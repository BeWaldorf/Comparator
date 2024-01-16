package com.waldorf.comparator.viewModel

import android.util.Log
import retrofit2.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waldorf.comparator.model.APICallData
import com.waldorf.comparator.model.SearchItem
import com.waldorf.comparator.network.ComparatorApi
import kotlinx.coroutines.launch
import java.io.IOException

class SearchItemViewModel(private var testFlag: Boolean? = false) :  ViewModel () {
    private var _searchItem: SearchItem? = null
    val searchItem: SearchItem? get() = _searchItem

    init {
        if(testFlag == null || testFlag == true){
            this._searchItem = SearchItem("1", "€20.00",
                "https://fastly.picsum.photos/id/910/536/354.jpg?hmac=tLOqOtDz3JUSQJfmxCuOdD54ipTd47kjh5FTgR2gCwQ",
                "https://fastly.picsum.photos/id/910/536/354.jpg?hmac=tLOqOtDz3JUSQJfmxCuOdD54ipTd47kjh5FTgR2gCwQ"
                )
        }
    }

    fun setSearchItem(searchItem: SearchItem) {
        _searchItem = searchItem
        searchItem.price = removeDotBetweenNumbers(searchItem.price)
    }
    fun getImageUrl(): String? {
        return searchItem?.imageLink
    }
    fun getTitleString(): String? {
        return searchItem?.name
    }
    fun getPriceString(): String? {
        return searchItem?.price
    }
    fun getCcString(): String? {
        return searchItem?.cc
    }
    fun getLinkString(): String? {
        return  searchItem?.link
    }

    private fun removeDotBetweenNumbers(inputString: String?): String {
        // Replace occurrences of "€x,.y" with "€x,y"
        if (inputString == null) {
            return "€ not parsed"
        }
        val string:String = inputString.replace(",.", ",")//.replace("€", "")
        Log.d("strings",string)
        return string
    }
}