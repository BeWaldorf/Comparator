package com.waldorf.comparator.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.waldorf.comparator.R
import com.waldorf.comparator.viewModel.HomeScreenViewModel
import com.waldorf.comparator.viewModel.SearchBarViewModel
import com.waldorf.comparator.viewModel.SearchItemViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeScreenView @Inject constructor(
    private val searchItemViewModelFactory: SearchItemViewModelFactory,
    private val viewModel: HomeScreenViewModel
){
    private val _searchItemView:SearchItemView = SearchItemView(searchItemViewModelFactory.create(30))
    val searchItemView: SearchItemView get() = _searchItemView

    //@Inject lateinit var viewModel : HomeScreenViewModel


    @Composable
    fun doodleNoodle(){}

    @Composable
    fun HomeScreen() {
        val mediumPadding = 16.dp
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(mediumPadding),
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
                    .padding(mediumPadding),
                verticalArrangement = Arrangement.spacedBy(mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(viewModel.searchBarVM, onUpdateViewModel = { newSearchBarViewModel ->
                    viewModel.searchBarVM = newSearchBarViewModel
                })
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "(knop)",
                        fontSize = 16.sp
                    )
                }
                searchItemView.searchItemCard()
            }
        }
    }

    @Composable
    fun SearchBar(searchBarViewModel: SearchBarViewModel, onUpdateViewModel: (SearchBarViewModel) -> Unit) {
       var isSearching by remember { mutableStateOf(false) }
       var searchText by remember { mutableStateOf("") }

       OutlinedTextField(
           value = searchText,
           singleLine = true,
           shape = MaterialTheme.shapes.large,
           modifier = Modifier.fillMaxWidth(),
           colors = TextFieldDefaults.colors(
               focusedContainerColor = MaterialTheme.colorScheme.surface,
               unfocusedContainerColor = MaterialTheme.colorScheme.surface,
               disabledContainerColor = MaterialTheme.colorScheme.surface,
           ),
           onValueChange = {
               searchText = it
               searchBarViewModel.updateSearchTerm(searchText)
               onUpdateViewModel(searchBarViewModel)
           },
           label = { Text(stringResource(id = R.string.search_bar_string)) },
           isError = false,
           keyboardOptions = KeyboardOptions.Default.copy(
               imeAction = ImeAction.Done
           ),
           keyboardActions = KeyboardActions(
               onDone = { }
           )
       )
    }
}
