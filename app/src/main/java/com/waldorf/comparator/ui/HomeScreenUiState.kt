package com.waldorf.comparator.ui

import androidx.compose.material3.ButtonColors
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.waldorf.comparator.viewModel.ApiViewModel

data class HomeScreenUiState(private val flag:Boolean
) {
    private var _homeFlag = MutableLiveData<Boolean>(true)
    val homeFlag: LiveData<Boolean> = _homeFlag
    init {
        _homeFlag.value = flag
    }
}