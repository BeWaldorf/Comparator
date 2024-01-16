package com.waldorf.comparator.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.waldorf.comparator.R
import com.waldorf.comparator.model.SearchItem
import com.waldorf.comparator.viewModel.SearchItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchItemView (private val _itemVM: SearchItemViewModel){
    val itemVM:SearchItemViewModel get() = _itemVM

    @Composable
    fun SearchItemCard(){
        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF800020),
                contentColor = Color(0xFF78B8E0)
            ),
            elevation =CardDefaults.cardElevation(3.dp),
            modifier = Modifier
                .height(50.dp)
                .width(50.dp),
        ) {Text(_itemVM.getCcString().toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            )}
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.padding(
                start = 4.dp, // Left padding
                top = 8.dp,    // Top padding
                end = 4.dp,   // Right padding
                bottom = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF800020),
                contentColor = Color(0xFF78B8E0)
            ),//onClick = { -> {
    // Log.d("card","item: ${itemVM.searchItem?.name}")
    //        } }
        ){
            SearchItemCardContent()
        }
        Log.d("compose_item","compose card drawn")
    }

    @Composable
    fun SearchItemCardContent (){
        val image = painterResource(id = R.drawable.img)
        Row{
            AsyncImage(
                model = _itemVM.getImageUrl(),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(_itemVM.getTitleString().toString())
                Spacer(modifier = Modifier.height(8.dp))
                Text(_itemVM.getPriceString().toString())
            }
        }
    }

    @Composable
    fun DetailCard(item: SearchItem){
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
                Text("Terug")
            }
        }
    }
}

