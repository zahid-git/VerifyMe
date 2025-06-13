package com.verifyme.app.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.verifyme.app.navigation.AppNavHost
import com.verifyme.app.presentation.theme.VerifyMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyMeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            VerifyMeTheme {
                AppNavHost(modifier = Modifier, navController = rememberNavController())
            }
        }
    }
}