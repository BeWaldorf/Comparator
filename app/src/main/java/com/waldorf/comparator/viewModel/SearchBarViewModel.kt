package com.waldorf.comparator.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waldorf.comparator.ui.HomeScreenUiState
import com.waldorf.comparator.ui.SearchBarUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchBarViewModel(val api_vm:ApiViewModel) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchBarUiState())
    val uiState: StateFlow<SearchBarUiState> = _uiState.asStateFlow()

    fun updateSearchTerm(searchText: String) {
        val isSearching = searchText.isNotEmpty()
        _uiState.value = SearchBarUiState(currentSearch = searchText, isSearching = isSearching)
        Log.d("SearchBar", "Current search term: $searchText")
        api_vm.setCallData(searchText)
    }
}

