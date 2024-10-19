package com.example.droidchat.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.droidchat.navigation.Router.SIGN_IN_ROUTE
import com.example.droidchat.navigation.Router.SIGN_UP_ROUTE
import com.example.droidchat.navigation.Router.SPLASH_ROUTE

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = SPLASH_ROUTE) {
        composable(SPLASH_ROUTE) {

        }
        composable(SIGN_IN_ROUTE){

        }
        composable(SIGN_UP_ROUTE) {

        }
    }
}