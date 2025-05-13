package com.verifyme.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.verifyme.app.presentation.screens.homepage.HomepageScreen
import com.verifyme.app.presentation.screens.login.LoginPageScreen
import com.verifyme.app.presentation.screens.profilepage.ProfilePageScreen
import com.verifyme.app.presentation.screens.splashscreen.SplashScreen
import com.verifyme.app.utils.Constants


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoutes.SplashScreen,
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
    ) {
        composable<NavRoutes.SplashScreen> { SplashScreen(navController = navController) }
        composable<NavRoutes.LoginPage> { LoginPageScreen(navController = navController) }
        composable<NavRoutes.HomePage> { HomepageScreen(navController = navController) }
        composable<NavRoutes.ProfilePage> { ProfilePageScreen(navController, it.toRoute<NavRoutes.ProfilePage>() ) }
    }
}
