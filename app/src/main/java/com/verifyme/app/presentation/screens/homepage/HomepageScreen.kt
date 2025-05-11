package com.verifyme.app.presentation.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.verifyme.app.presentation.theme.VerifyMeTheme

@Composable
fun HomepageScreen(
    navController: NavHostController
) {

}


@Preview
@Composable
fun ShowHomePage(){

    VerifyMeTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {

        }
    }

}