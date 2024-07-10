package com.example.twitter.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.twitter.ItemView.TweetItem
import com.example.twitter.ItemView.UserItem
import com.example.twitter.viewmodel.HomeViewModel
import com.example.twitter.viewmodel.SearchViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Search(navHostController: NavHostController){
    val SearchViewModel: SearchViewModel = viewModel()
    val Users by SearchViewModel.Users.observeAsState()
    val context = LocalContext.current
    var search by remember{
        mutableStateOf("")
    }
    Column {
        OutlinedTextField(
            value = search,
            onValueChange = {search=it},
            label = { Text(text = "Search")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null)}
        )
        Spacer(modifier=Modifier.height(16.dp))
        LazyColumn {
            if (Users != null && Users!!.isNotEmpty()) {
                var filterItems = Users!!.filter { it.name!!.contains(search, ignoreCase = true) }

                items(filterItems ?: emptyList()) { pairs ->
                    UserItem(
                        pairs,
                        navHostController
                    )
                }
            }
        }
    }
}