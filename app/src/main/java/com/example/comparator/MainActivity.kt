package com.example.comparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.comparator.viewModel.HomeScreenViewModel
import com.example.comparator.views.HomeScreenView


class MainActivity : ComponentActivity() {
    private val _homeScreenView:HomeScreenView = HomeScreenView(HomeScreenViewModel())
    val homeScreenView: HomeScreenView get() = _homeScreenView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Text("Hello World!")
            //MessageCard(Message("Android", "Jetpack Compose"))
            homeScreenView.HomeScreen()
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
