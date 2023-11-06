package com.example.comparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comparator.ui.theme.ComparatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Text("Hello World!")
            //MessageCard(Message("Android", "Jetpack Compose"))
            SearchBar(query = doodle) {
                
            }
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
fun ProcudctSearher(){}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!"))
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComparatorTheme {
        Greeting("Android")
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    var isSearching by remember { mutableStateOf(false) }

    BasicTextField(
        value = query,
        onValueChange = {
            onQueryChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        textStyle = TextStyle(color = Color.Black),
        cursorBrush = SolidColor(Color.Black),
        singleLine = true,
        maxLines = 1,
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    SearchBar(
        query = query,
        onQueryChange = { newQuery ->
            query = newQuery
        }
    )
}