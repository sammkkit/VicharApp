package com.example.twitter.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.twitter.ItemView.ProfileTweetItem
import com.example.twitter.ItemView.TweetItem
import com.example.twitter.navigation.Routes
import com.example.twitter.utils.SharedPref
import com.example.twitter.viewmodel.AuthViewModel
import com.example.twitter.viewmodel.HomeViewModel
import com.example.twitter.viewmodel.SearchViewModel
import com.example.twitter.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Profile(navHostController: NavHostController) {

    val authViewModel: AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)
    LaunchedEffect(firebaseUser) {
        if (firebaseUser == null) {
            navHostController.navigate(Routes.Login.route) {
                popUpTo(navHostController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }
    val userViewModel:UserViewModel = viewModel()
//    val SearchViewModel: SearchViewModel = viewModel()
//    val Users by SearchViewModel.Users.observeAsState()
    val context = LocalContext.current
    userViewModel.fetchTweets()
    val tweets by userViewModel.tweets.observeAsState(emptyList())
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val photopickerlauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            selectedImageUri = it
        }
    )
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter =
                    rememberAsyncImagePainter(model = SharedPref.getimage(context)),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        photopickerlauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                        .size(80.dp)
                        .clip(shape = RoundedCornerShape(40.dp))
                        ,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row {
                        Text(
                            text = SharedPref.getName(context),
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 40.sp
                            )
                        )

                    }
                    Text(
                        text = "@${SharedPref.getUserName(context)}",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Bio: ${SharedPref.getbio(context)}",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp
                        )

                    )

                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {authViewModel.logout()}
                , colors = ButtonDefaults.buttonColors(Color.White)) {
                    Text("Log out")
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider()
        LazyColumn(

        ) {
            items(tweets.reversed()){
                ProfileTweetItem(tweetModel = it)
            } 
        }
    }
}