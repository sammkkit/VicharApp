package com.example.twitter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.twitter.screens.AddTweet
import com.example.twitter.screens.BottomNav
import com.example.twitter.screens.Home
import com.example.twitter.screens.Login
import com.example.twitter.screens.Notification
import com.example.twitter.screens.Profile
import com.example.twitter.screens.Search
import com.example.twitter.screens.SignUp
import com.example.twitter.screens.Splash

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) {
            Splash(navController)
        }
        composable(Routes.Home.route) {
            Home(navController)
        }
        composable(Routes.Search.route) {
            Search(navController)
        }
        composable(Routes.AddTweet.route) {
            AddTweet(navController)
        }
        composable(Routes.Notification.route) {
            Notification()
        }
        composable(Routes.Profile.route) {
            Profile(navController)
        }
        composable(Routes.BottomNav.route) {
            BottomNav(navController = navController)
        }
        composable(Routes.Login.route) {
            Login(navController)
        }
        composable(Routes.Signup.route) {
            SignUp(navController)
        }

    }
}