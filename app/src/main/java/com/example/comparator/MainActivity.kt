package com.example.comparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.comparator.viewModel.HomeScreenViewModel
import com.example.comparator.views.HomeScreen
import com.example.comparator.viewModel.SearchItemViewModel


class MainActivity : ComponentActivity() {

    val homescreenVM = HomeScreenViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Text("Hello World!")
            //MessageCard(Message("Android", "Jetpack Compose"))
            HomeScreen(homescreenVM)
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row {
        //Image(
        //    painter = painterResource(R.drawable.profile_picture),
        //    contentDescription = "Contact profile picture",
        //)
        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
}
@Composable
fun SearchItem(searchItemVM: SearchItemViewModel) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Image(
                painter = Painter(searchItemVM.imageUrl),
                contentDescription = searchItemVM.description,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                searchItemVM.description
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "€${searchItemVM.price * searchItemVM.quantity}"
            )
        }
    }
}