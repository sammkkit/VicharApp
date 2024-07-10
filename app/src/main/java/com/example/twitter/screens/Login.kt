package com.example.twitter.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.twitter.navigation.Routes
import com.example.twitter.viewmodel.AuthViewModel

@Composable
fun Login(
    navController: NavHostController,
    ){
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    val authViewModel: AuthViewModel = viewModel()
    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)
    val context = LocalContext.current
    val error by authViewModel.error.observeAsState()
    LaunchedEffect(firebaseUser){
        if(firebaseUser!=null){
            navController.navigate(Routes.BottomNav.route){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop=true
            }
        }
    }
    error?.let {
        Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
    }
    
    Column(
        modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Login", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = email.trim(),
            onValueChange = {email=it},
            label = { Text(text = "Email")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password.trim(),
            onValueChange = {password=it},
            label = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(context,"Enter All fields",Toast.LENGTH_SHORT).show()
            }else{
                authViewModel.login(email, password,context)
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(text = "Sign in")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Don't have an account? Sign up.",
            modifier = Modifier
                .clickable {
                    navController.navigate(Routes.Signup.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop=true
                    }
                }
                ,
        )
    }
}

@Preview
@Composable
fun loginview(){
    Login(navController = rememberNavController())
}