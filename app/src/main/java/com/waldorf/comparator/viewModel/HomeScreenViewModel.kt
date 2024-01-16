package com.waldorf.comparator.viewModel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waldorf.comparator.model.SearchItem
import com.waldorf.comparator.ui.ApiUIState
import com.waldorf.comparator.ui.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeScreenViewModel: ViewModel() {
   private val _uiState = MutableStateFlow(HomeScreenUiState(true))
   val uiState: MutableStateFlow<HomeScreenUiState> = _uiState
}


//   fun searchForItem(){
//     viewModelScope.launch{
//         _apiVM.makeApiCall()
//     }
//   }