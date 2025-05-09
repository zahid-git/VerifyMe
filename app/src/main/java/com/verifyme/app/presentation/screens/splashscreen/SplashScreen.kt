package com.verifyme.app.presentation.screens.splashscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.verifyme.app.navigation.NavRoute


@Composable
fun SplashScreen(
    navController: NavHostController
){

    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                navController.navigate(NavRoute.LoginPage)
            }
        ) {
            Text(
                text = "Click now"
            )
        }
    }


}