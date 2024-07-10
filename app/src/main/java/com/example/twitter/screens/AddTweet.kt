package com.example.twitter.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.twitter.R
import com.example.twitter.navigation.Routes
import com.example.twitter.utils.SharedPref
import com.example.twitter.viewmodel.AddTweetViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTweet(navHostController: NavHostController) {
    val context = LocalContext.current
    var tweet by remember{
        mutableStateOf("")
    }
    val tweetViewModel:AddTweetViewModel= viewModel()
    val isPosted by tweetViewModel.isPosted.observeAsState()
    LaunchedEffect(isPosted){
        if(isPosted!!){
            tweet=""
            Toast.makeText(context,"Tweeted",Toast.LENGTH_SHORT).show()

            navHostController.navigate(Routes.Home.route){
                popUpTo(Routes.AddTweet.route){
                    inclusive=true
                }

            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
            }
            Text(
                "New Vichar",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = rememberAsyncImagePainter(model = SharedPref.getimage(context)),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(25.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = SharedPref.getName(context),
                style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 20.sp)
            )
        }
        OutlinedTextField(
            value = tweet,
            onValueChange = {tweet=it},
            placeholder = {Text("What's happening ?")},
            modifier= Modifier
                .fillMaxWidth()
                .padding(start = 48.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent
            )
        )
        Button(
            onClick = {
                if(tweet.trim()==""){
                    Toast.makeText(context,"Enter Tweet",Toast.LENGTH_SHORT).show()
                    tweet=""
                }else{

                      tweetViewModel.saveData(tweet,FirebaseAuth.getInstance().currentUser!!.uid)
                }
            }
            ,modifier=Modifier.padding(start = 280.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.light_blue)
            )
        ) {
            Text("Post")
        }
    }
}
