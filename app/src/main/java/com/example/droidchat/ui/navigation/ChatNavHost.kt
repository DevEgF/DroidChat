package com.example.droidchat.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.droidchat.ui.navigation.extension.slideInTo
import com.example.droidchat.ui.navigation.extension.slideOutTo
import com.example.droidchat.ui.feature.signin.SignInRoute
import com.example.droidchat.ui.feature.signup.SignUpRouteRoute
import com.example.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.SplashRoute) {
        composable<Route.SplashRoute>(
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
                        route = Route.SignInRoute,
                        navOptions = navOptions {
                            popUpTo(Route.SplashRoute){
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }
        composable<Route.SignInRoute>(
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
                    navController.navigate(Route.SignUpRoute)
                },
                navigateToMain = {

                }
            )
        }
        composable<Route.SignUpRoute>(
            enterTransition =  {
                this.slideInTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Left,
                )
            },
            exitTransition = {
                this.slideOutTo(
                    direction = AnimatedContentTransitionScope.SlideDirection.Right,
                )
            }
        ) {
            SignUpRouteRoute(
                onSignUpSuccess = {
                    navController.popBackStack()
                }
            )
        }
    }
}