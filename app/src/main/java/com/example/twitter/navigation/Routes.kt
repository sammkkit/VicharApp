package com.example.twitter.navigation

sealed class Routes(val route:String) {
    object Home: Routes("home")
    object Notification: Routes("notification")
    object Profile: Routes("profile")
    object Search: Routes("search")
    object Splash: Routes("splash")
    object AddTweet: Routes("add_tweet")
    object BottomNav: Routes("bottom_nav")
    object Login: Routes("login")
    object Signup: Routes("signup")
}