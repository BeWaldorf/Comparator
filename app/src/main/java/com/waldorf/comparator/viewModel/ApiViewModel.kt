package com.waldorf.comparator.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.waldorf.comparator.model.APICallData
import com.waldorf.comparator.model.SearchItem
import com.waldorf.comparator.network.ComparatorApi
import com.waldorf.comparator.ui.ApiUIState
import com.waldorf.comparator.ui.ApiUIState.*
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class ApiViewModel(): ViewModel() {
    private var _apiCallData: APICallData = APICallData(null)
    val apiCallData:APICallData get() = _apiCallData

    private val _apiUIState =  MutableStateFlow<ApiUIState>(Empty)
    val apiUIState: MutableStateFlow<ApiUIState> = _apiUIState

    init {
        _apiUIState.value = Empty
    }

    fun setCallData(searchTerm: String, regions: Array<String>  = arrayOf("BE", "NL", "DE", "FR","JP", "COM")){
        _apiCallData = APICallData(searchTerm, regions)
    }

    fun setApiUIState(state:ApiUIState){
        _apiUIState.value = state
    }

    suspend fun makeApiCall() {
        _apiUIState.value = Loading
        try {
            Log.d("api","itemterm: ${_apiCallData.searchString}")
            val listResult: MutableList<SearchItem> = mutableListOf()
            for(cc in _apiCallData.regions){
                val itemsForRegion: List<SearchItem> = ComparatorApi.retrofitService.getItem(cc, _apiCallData.searchString)
                for(item in itemsForRegion){
                    item.cc = cc
                    Log.d("api","item: ${item.name}")
                }
                listResult += itemsForRegion
            }
            _apiUIState.value = Success(listResult)
        } catch (e: IOException){
            Log.d("Error","IOError")
            _apiUIState.value = Error
        } catch (e: HttpException){
            Log.d("Error","HTTPError")
            _apiUIState.value = Error
        }
    }
}