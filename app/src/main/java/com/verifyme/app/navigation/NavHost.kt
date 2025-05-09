package com.verifyme.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.verifyme.app.presentation.screens.login.LoginPageScreen
import com.verifyme.app.presentation.screens.splashscreen.SplashScreen
import com.verifyme.app.utils.Constants


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoute.SplashScreen
    ) {

        composable<NavRoute.SplashScreen> (
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(
                        durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                        delayMillis = Constants.SCREEN_TRANSITION_DELAY
                    )
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(
                        durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                        delayMillis = Constants.SCREEN_TRANSITION_DELAY
                    )
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(
                        durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                        delayMillis = Constants.SCREEN_TRANSITION_DELAY
                    )
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(
                        durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                        delayMillis = Constants.SCREEN_TRANSITION_DELAY
                    )
                )
            },
        ) {
            SplashScreen(
                navController = navController
            )
        }

        composable<NavRoute.LoginPage> {
            LoginPageScreen(
                navController = navController
            )
        }
    }
}


/*fun NavGraphBuilder.commonComposable(
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    return composable (
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = Constants.SCREEN_TRANSITION_DURATION,
                    delayMillis = Constants.SCREEN_TRANSITION_DELAY
                )
            )
        }
    )
}*/

