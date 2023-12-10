package com.waldorf.comparator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waldorf.comparator.ui.HomeScreenUiState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchBarViewModel @Inject constructor()
     : ViewModel() {

    private val _searchTerm = MutableLiveData<String>()
    val searchTerm: LiveData<String> get() = _searchTerm
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    fun setSearchTerm(term: String) {
        _searchTerm.value = term
    }
    init {
        resetSearch()
    }
    fun resetSearch(){

    }

    fun updateSearchTerm(searchText: String) {

    }
}