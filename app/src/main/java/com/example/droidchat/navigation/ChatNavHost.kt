package com.example.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.ui.feature.signin.SignInRoute
import com.example.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = SignInRoute,
                        navOptions = navOptions {
                            popUpTo(SplashRoute){
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }
        composable<SignInRoute> {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(SignUpRoute)
                }
            )
        }
        composable<SignUpRoute> {

        }
    }
}