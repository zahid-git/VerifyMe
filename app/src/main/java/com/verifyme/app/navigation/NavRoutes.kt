package com.verifyme.app.navigation

import kotlinx.serialization.Serializable


object NavRoutes {
    const val SPLASH_SCREEN_ROUTE = "nav_spalsh_screen"
    const val LOGIN_SCREEN_ROUTE = "nav_login_screen"
}


sealed class NavScreen (val route: String){

    data object Splash: NavScreen(route = NavRoutes.SPLASH_SCREEN_ROUTE)
    data object Login: NavScreen(route = NavRoutes.LOGIN_SCREEN_ROUTE)

}


data object NavRoute {

    @Serializable
    object SplashScreen

    @Serializable
    object LoginPage

}