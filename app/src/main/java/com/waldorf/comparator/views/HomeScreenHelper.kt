package com.waldorf.comparator.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.waldorf.comparator.R
import com.waldorf.comparator.model.SearchItem
import com.waldorf.comparator.ui.ApiUIState
import com.waldorf.comparator.viewModel.SearchItemViewModel

class HomeScreenHelper {

    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }

    @Composable
    fun ErrorScreen(retryAction: (() -> Unit)? = null, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_connection_error),
                contentDescription = ""
            )
            Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
            if (retryAction != null) {
                Button(onClick = retryAction) {
                    Text(stringResource(R.string.retry))
                }
            }
        }
    }


    @Composable
    fun ApiLoader(liveApiUIState: LiveData<ApiUIState>, modifier: Modifier = Modifier){
        when (liveApiUIState.value) {
            is ApiUIState.Empty     -> EmptyBlock()
            is ApiUIState.Loading   -> LoadingScreen(modifier = modifier.fillMaxSize())
            is ApiUIState.Success   -> ItemListMaker((liveApiUIState.value as ApiUIState.Success).itemList, modifier)
            is ApiUIState.Error     -> ErrorScreen(modifier = modifier.fillMaxSize())
            else -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }

    @Composable
    fun EmptyBlock(){
        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF800020),
                contentColor = Color(0xFF78B8E0)
            ),
            elevation = CardDefaults.cardElevation(3.dp),
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
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
}