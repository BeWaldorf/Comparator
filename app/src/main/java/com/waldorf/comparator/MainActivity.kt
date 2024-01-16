package com.waldorf.comparator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.waldorf.comparator.viewModel.HomeScreenViewModel
import com.waldorf.comparator.views.HomeScreenView
import com.waldorf.comparator.service.LoginService
import com.waldorf.comparator.viewModel.ApiViewModel

class MainActivity : ComponentActivity() {
    private val homeScreenVM: HomeScreenViewModel = HomeScreenViewModel()
    private val apiVM:ApiViewModel = ApiViewModel()
    private val _homeScreenView:HomeScreenView = HomeScreenView(homeScreenVM, apiVM)
    val homeScreenView: HomeScreenView get() = _homeScreenView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //login
        setContent {
            homeScreenView.ActivityContent()
        }
    }
}