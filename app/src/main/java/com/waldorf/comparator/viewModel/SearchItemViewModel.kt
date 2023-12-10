package com.waldorf.comparator.viewModel

import androidx.lifecycle.ViewModel
import com.waldorf.comparator.model.SearchItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchItemViewModel @AssistedInject constructor(
    @Assisted private val testFlag: Int? = null
) : ViewModel()  {

    interface AssistedFactory {
        fun getTestFlag(): Int?
    }

    private var _searchItem: SearchItem? = null
    val searchItem: SearchItem? get() = _searchItem

    init {
        //val testFlag = assistedFactory.getTestFlag()
        if(testFlag != null){
            this._searchItem = SearchItem(1,
                "DoodleNoodle",
                "https://fastly.picsum.photos/id/910/536/354.jpg?hmac=tLOqOtDz3JUSQJfmxCuOdD54ipTd47kjh5FTgR2gCwQ",
                "€69,69")
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

@AssistedFactory
interface SearchItemViewModelFactory {
    fun create(testFlag: Int?): SearchItemViewModel
}
