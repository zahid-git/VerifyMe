package com.verifyme.app.presentation.screens.login

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.verifyme.app.navigation.NavRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


@Composable
fun LoginPageScreen(
    navController: NavHostController
){


    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            "Hello"
        )

    }



}