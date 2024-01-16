package com.waldorf.comparator.ui

import com.waldorf.comparator.model.SearchItem

sealed interface ApiUIState{
    data class Success(val itemList: List<SearchItem>): ApiUIState
    object Error    : ApiUIState
    object Loading  : ApiUIState

    object Empty : ApiUIState
}
