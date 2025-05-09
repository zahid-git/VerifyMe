package com.verifyme.app.presentation.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.verifyme.app.R
import com.verifyme.app.navigation.NavRoutes
import com.verifyme.app.presentation.theme.VerifyMeTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    ShowSplashPage(navController, modifier)

}

@Preview
@Composable
fun ShowSplashPage(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    VerifyMeTheme {
        Column (
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center
        ) {

            Column (
                modifier = modifier
                    .wrapContentWidth()
                    .padding(start = 25.dp, end = 25.dp),
            ) {
                Image(
                    modifier = modifier
                        .height(250.dp)
                        .width(250.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.splash_screen_image),
                    contentDescription = ""
                )

                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    text = "IDENTITY VERIFICATION TO ACCELERATE TRUST WORLDWIDE"
                )
                Text(
                    modifier = modifier
                        .padding(top = 30.dp)
                        .wrapContentWidth(),
                    textAlign = TextAlign.Center,
                    text = "Experience KYC - an advanced e-KYC solution for seamless customer verification. Utilizing cutting-edge tech, ensure compliance and elevate onboarding."
                )
                Button(
                    modifier = modifier
                        .padding(top = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        navController.navigate(NavRoutes.LoginPage)
                    },
                ) {
                    Text(
                        text = "Lets get started",
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    }
}