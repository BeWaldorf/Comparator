package com.waldorf.comparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.waldorf.comparator.view.HomeScreenView
import com.waldorf.comparator.viewModel.HomeScreenViewModel
import com.waldorf.comparator.viewModel.SearchItemViewModel
import com.waldorf.comparator.viewModel.SearchItemViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
   private val _homeScreenView: HomeScreenView
): ComponentActivity() {
    //private val _homeScreenView:HomeScreenView = HomeScreenView()
    val homeScreenView: HomeScreenView get() = _homeScreenView
    //@Inject lateinit var homeScreenView : HomeScreenView


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
