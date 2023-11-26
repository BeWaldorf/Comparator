package com.example.comparator.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.comparator.ui.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeScreenViewModel: ViewModel() {
    var searchBarVM = SearchBarViewModel()
    private val _uiState = MutableStateFlow(HomeScreenUiState())


    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    private val _searchBarViewModel: MutableLiveData<SearchBarViewModel> =  MutableLiveData<SearchBarViewModel>()
    val searchBarViewModel: LiveData<SearchBarViewModel> get()= _searchBarViewModel
    fun setSearchBarViewModel(searchBarViewModel: SearchBarViewModel) {
        _searchBarViewModel.value = searchBarViewModel
    }

    init{}
}