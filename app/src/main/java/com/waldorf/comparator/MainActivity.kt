package com.waldorf.comparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.waldorf.comparator.viewModel.HomeScreenViewModel
import com.waldorf.comparator.views.HomeScreenView
import com.waldorf.comparator.service.LoginService

class MainActivity : ComponentActivity() {
    private val _homeScreenView:HomeScreenView = HomeScreenView(HomeScreenViewModel())
    val homeScreenView: HomeScreenView get() = _homeScreenView


    public override fun onStart() {
    super.onStart()
    // Check if user is signed in (non-null) and update UI accordingly.


}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //login
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
