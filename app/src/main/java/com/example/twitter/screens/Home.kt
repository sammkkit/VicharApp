package com.example.twitter.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.twitter.ItemView.TweetItem
import com.example.twitter.R
import com.example.twitter.viewmodel.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(navHostController: NavHostController){
    val homeViewModel:HomeViewModel= viewModel()
    val TweetAndUsers by homeViewModel.tweetAndUsers.observeAsState(null)
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize().background(Color.White)
    ){
        Row(
            horizontalArrangement = Arrangement.Center
            , verticalAlignment = Alignment.CenterVertically
            , modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(63.dp)
        ){

            Text("Vichar",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                )
            )
        }
        LazyColumn {
            items(TweetAndUsers ?: emptyList()) { pairs ->
                TweetItem(
                    tweetModel = pairs.first,
                    userModel = pairs.second,
                )
            }
        }
    }
}

