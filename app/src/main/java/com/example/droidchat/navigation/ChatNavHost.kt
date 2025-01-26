package com.example.droidchat.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.navigation.extension.slideInTo
import com.example.droidchat.navigation.extension.slideOutTo
import com.example.droidchat.ui.feature.signin.SignInRoute
import com.example.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = SplashRoute) {
        composable<SplashRoute>(
            enterTransition =  {
                this.slideInTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Right,
                )
            },
            exitTransition = {
                this.slideOutTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Left,
                )
            }
        ) {
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
        composable<SignInRoute>(
            enterTransition =  {
                this.slideInTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Right,
                )
            },
            exitTransition = {
                this.slideOutTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Left,
                )
            }
        ) {
            SignInRoute(
                navigateToSignUp = {
                    navController.navigate(SignUpRoute)
                }
            )
        }
        composable<SignUpRoute>(
            enterTransition =  {
                this.slideInTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Left,
                )
            },
            exitTransition = {
                this.slideOutTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Left,
                )
            }
        ) {}
    }
}