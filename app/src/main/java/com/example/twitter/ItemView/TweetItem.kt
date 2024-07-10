package com.example.twitter.ItemView

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.twitter.R
import com.example.twitter.model.TweetModel
import com.example.twitter.model.UserModel
import com.example.twitter.utils.SharedPref
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun TweetItem(
    tweetModel: TweetModel,
    userModel: UserModel
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp,end = 8.dp,top = 4.dp,bottom=4.dp)
        ) {
            Image(
                painter =
                rememberAsyncImagePainter(model = userModel.toString),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape = RoundedCornerShape(25.dp))
                    ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Row {
                    Text(
                        text = userModel.name,
                        style = TextStyle(fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color.Black)
                    )
                    Text(
                        text = "@${userModel.username}",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = tweetModel.tweet,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.scrim
                    )

                )
                Spacer(modifier = Modifier.height(8.dp))
                val timestamp = tweetModel.timestamp.toLong()
                val sdf = SimpleDateFormat("HH:mm")
                val formattedTime = sdf.format(Date(timestamp))
                Text(text= formattedTime,color=Color.Black)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
    }
}
