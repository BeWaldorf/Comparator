package com.waldorf.comparator.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.observe
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
//import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import coil.compose.AsyncImage
import com.waldorf.comparator.R
import com.waldorf.comparator.model.SearchItem
import com.waldorf.comparator.ui.ApiUIState
import com.waldorf.comparator.ui.HomeScreenUiState
import com.waldorf.comparator.viewModel.ApiViewModel
import com.waldorf.comparator.viewModel.HomeScreenViewModel
import com.waldorf.comparator.viewModel.SearchBarViewModel
import com.waldorf.comparator.viewModel.SearchItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeScreenView(private val viewModel: HomeScreenViewModel, private val apiVM: ApiViewModel){
    private var searchBarVM:SearchBarViewModel? = null
    private var homeFlagState = MutableStateFlow(viewModel.uiState.value.homeFlag.value)
    private val apiState = MutableStateFlow(apiVM.apiUIState.value)

    init {
        searchBarVM = SearchBarViewModel(apiVM)

        // Observe changes in homeFlag and update homeFlagState accordingly
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.uiState.collect { homeScreenUiState ->
                homeFlagState.value = homeScreenUiState.homeFlag.value
                }
        }
        CoroutineScope(Dispatchers.Main).launch {
            homeFlagState.collect{boolean ->
                homeFlagState.value = boolean
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            apiVM.apiUIState.collect { newState ->
                apiState.value = newState
            }
        }
    }
    @Composable
    fun ActivityContent(){
        Container()
    }

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun Container() {
        Log.d("views", "doodleNoodleBoolde ${homeFlagState.value}")
        Log.d("views", "${homeFlagState.value}")

        when {
            homeFlagState.value!! -> {
                Log.d("views", "home state: ${homeFlagState.value}")
                HomeScreen()
            }
            (!homeFlagState.value!!)-> {
                Log.d("views", "home state: ${homeFlagState.value}")
                val itemVm: SearchItemViewModel = SearchItemViewModel(true)
                Details(item = itemVm.searchItem!!)
            }
            else -> {
                Log.d("views", "home state: ${homeFlagState.value}")
                val itemVm: SearchItemViewModel = SearchItemViewModel(true)
                Details(item = itemVm.searchItem!!)
            }
        }
    }

    @Composable
    fun HomeScreen(){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(searchBarVM!!, onUpdateViewModel = {newSearchBarViewModel ->
                    searchBarVM = newSearchBarViewModel
                })
                OutlinedButton(
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            apiVM.makeApiCall()
                        }},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA30A0A),
                        contentColor = Color(0xFF51CBD6),
                        disabledContainerColor = Color(0xFF813131),
                        disabledContentColor = Color(0xFF4C6568)
                    )
                ) {
                    Text(
                        //color = Color(0xFF78B8DF),
                        text = stringResource(id = R.string.search_button),
                        fontSize = 16.sp
                    )
                }
            }
            ApiBlock()
        }

    }

    @Composable
    fun SearchBar(searchBarViewModel: SearchBarViewModel, onUpdateViewModel: (SearchBarViewModel) -> Unit) {
        val uiState by searchBarViewModel.uiState.collectAsState()

        OutlinedTextField(
            value = uiState.currentSearch,
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF970F0F),
                unfocusedContainerColor = Color(0xFF8FB6CE),
                disabledContainerColor = Color(0xFF56656F),
                cursorColor = Color(0xFFFFFFFF),
                focusedTextColor =Color(0xFF7CC8DB),
                unfocusedTextColor = Color(0xFF970F0F)
            ),
            onValueChange = {
                searchBarViewModel.updateSearchTerm(it)
                onUpdateViewModel(searchBarViewModel)
            },
            label = { Text(stringResource(id = R.string.search_bar_string)) },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        )
    }

    @Composable
    fun ApiBlock() {
        val apiState by apiState.collectAsState()

        when (apiState) {
            is ApiUIState.Success -> {
                ItemListMaker((apiState as ApiUIState.Success).itemList)
            }
            ApiUIState.Loading -> {
                LoadingScreen()
            }
            ApiUIState.Error -> {
                ErrorScreen()
            }
            ApiUIState.Empty -> {
                EmptyBlock()
            }
        }
    }

    @Composable
    fun Details(item:SearchItem){
        val image = painterResource(id = R.drawable.img)
        Column{
            AsyncImage(
                model = item.imageLink,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                Text(item.name)
                Spacer(modifier = Modifier.height(8.dp))
                Text(item.price.toString())
            }
            Button(
                onClick ={},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA30A0A),
                    contentColor = Color(0xFF51CBD6),
                    disabledContainerColor = Color(0xFF813131),
                    disabledContentColor = Color(0xFF4C6568)
                ),){
                Text(stringResource(id = R.string.back))
            }
        }
    }

    @Composable
    fun EmptyBlock(){
        Card(
            modifier = Modifier
                .height(1.dp)
                .width(1.dp)
        ){}
    }

    @Composable
    fun ItemListMaker(itemList:List<SearchItem>, modifier: Modifier = Modifier){
        Log.d("ViewCard","API Card start")
        for( item in itemList){
            val itemVM: SearchItemViewModel = SearchItemViewModel()
            itemVM.setSearchItem(item)
            val itemView: SearchItemView = SearchItemView(itemVM)
            itemView.SearchItemCard()
        }
    }

    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(199.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

    @Composable
    fun ErrorScreen(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = ""
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(15.dp))
            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        apiVM.makeApiCall()
                }},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA30A0A),
                    contentColor = Color(0xFF51CBD6),
                    disabledContainerColor = Color(0xFF813131),
                    disabledContentColor = Color(0xFF4C6568)
                ),) {
                Text(stringResource(R.string.retry))
            }
        }
        Button(
            onClick = {
                Log.d("error","clicked Details")
                homeFlagState = homeFlagState.not()
                Log.d("error","${homeFlagState.value}")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFA30A0A),
                contentColor = Color(0xFF51CBD6),
                disabledContainerColor = Color(0xFF813131),
                disabledContentColor = Color(0xFF4C6568)
            ),) {
            Text(stringResource(R.string.detail))
        }
    }

}

operator fun MutableStateFlow<Boolean?>.not(): MutableStateFlow<Boolean?> {
    value = when (value) {
        true -> false
        false -> true
        else -> null
    }
    return this
}
