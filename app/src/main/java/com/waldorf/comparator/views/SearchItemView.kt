package com.waldorf.comparator.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.waldorf.comparator.R
import com.waldorf.comparator.viewModel.SearchItemViewModel



class SearchItemView (private val itemVM: SearchItemViewModel){

    @Composable
    fun searchItemCard(){
        Card(
            shape = RoundedCornerShape(4.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.padding(16.dp)
        ){
            searchItemCardContent()
        }
    }

    @Composable
    fun searchItemCardContent (){
        val image = painterResource(id = R.drawable.img)
        Row{
            AsyncImage(
                model = itemVM.getImageUrl(),
                contentDescription = null,
                modifier = Modifier.width(200.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(itemVM.getTitleString().toString())
                Spacer(modifier = Modifier.height(8.dp))
                Text(itemVM.getFullPriceString().toString())
            }
        }
    }
}

