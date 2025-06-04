package com.verifyme.app.navigation

import kotlinx.serialization.Serializable


data object NavRoutes {

    @Serializable object SplashScreen
    @Serializable object LoginPage
    @Serializable object HomePage
    @Serializable data class ProfilePage (
        var storeId: String
    )

}


