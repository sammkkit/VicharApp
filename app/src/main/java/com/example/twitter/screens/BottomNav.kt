package com.example.twitter.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.example.twitter.R
import com.example.twitter.navigation.Routes

@Composable
fun BottomNav(navController: NavHostController) {
    val list = listOf<SmoothAnimationBottomBarScreens>(
        SmoothAnimationBottomBarScreens(
            Routes.Home.route,
            "Home",
            Icons.Rounded.Home
        ),
        SmoothAnimationBottomBarScreens(
            Routes.Search.route,
            "Search",
            Icons.Rounded.Search
        ),
        SmoothAnimationBottomBarScreens(
            Routes.AddTweet.route,
            "Vichar",
            Icons.Rounded.Add
        ),
        SmoothAnimationBottomBarScreens(
            Routes.Profile.route,
            "Profile",
            Icons.Rounded.Person
        ),

        )
    val navController1 = rememberNavController()
    val currentIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    Scaffold(
        bottomBar = {
            SmoothAnimationBottomBar(
                navController = navController1,
                bottomNavigationItems = list,
                initialIndex = currentIndex,
                BottomBarProperties(backgroundColor = Color.Black, iconTintColor = Color.White),
                onSelectItem = {
                    Log.i("SELECTED_ITEM", "onCreate: Selected Item ${it.name}")
                }
            )
        }
    ) {
        NavHost(
            navController = navController1,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Routes.Home.route){
                Home(navController)
            }

            composable(Routes.Search.route){
                Search(navController)
            }
            composable(Routes.AddTweet.route){
                AddTweet(navController1)
            }
            composable(Routes.Notification.route){
                Notification()
            }
            composable(Routes.Profile.route){
                Profile(navController)
            }

        }

    }
}

//OLD CODE (OF BOTTOM BAR)

//@Composable
//fun MyBottomBar(navController1: NavHostController) {
//
//
//    val backStackEntry = navController1.currentBackStackEntryAsState()
//    val list = listOf<SmoothAnimationBottomBarScreens>(
//        SmoothAnimationBottomBarScreens(
//            Routes.Home.route,
//            "Home",
//            Icons.Rounded.Home
//        ),
//        SmoothAnimationBottomBarScreens(
//            Routes.Search.route,
//            "Search",
//            Icons.Rounded.Search
//        ),
//        SmoothAnimationBottomBarScreens(
//            Routes.AddTweet.route,
//            "Tweet",
//            Icons.Rounded.Add
//        ),
//        SmoothAnimationBottomBarScreens(
//            Routes.Notification.route,
//            "Notifications",
//            Icons.Rounded.Notifications
//        ),
//        SmoothAnimationBottomBarScreens(
//             Routes.Profile.route,
//             "Profile",
//             Icons.Rounded.Person
//        ),
//
//        )
//
//    BottomAppBar(
//        containerColor = colorResource(id = R.color.twitter_black),
//        contentColor = Color.White
//    ) {
//        list.forEach {
//            val selected = it.route == backStackEntry?.value?.destination?.route
//
//            NavigationBarItem(selected = selected,
//                onClick = {
//                    navController1.navigate(it.route)
//                    {
//                        popUpTo(navController1.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                    }
//                },
//                icon = {
//                    Icon(
//                        imageVector = it.Icon,
//                        contentDescription = it.title,
//                        tint = Color.White
//                        , modifier = Modifier
//                            .size(
//                            if(selected){
//                                34.dp
//                            }else{
//                                26.dp
//                            }
//                        )
//                    )
//                },
//                colors = NavigationBarItemDefaults.colors(indicatorColor = colorResource(id = R.color.twitter_black))
//            )
//        }
//    }
//}