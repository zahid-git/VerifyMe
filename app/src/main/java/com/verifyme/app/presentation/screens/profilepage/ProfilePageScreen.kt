package com.verifyme.app.presentation.screens.profilepage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.verifyme.app.navigation.NavRoutes


@Composable
fun ProfilePageScreen(
    navController: NavHostController,
    args: NavRoutes.ProfilePage
) {
    val storeId = args.storeId
    var viewModel: ProfilePageViewModel = hiltViewModel()
    val viewState by viewModel.profileViewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateStoreId(storeId = storeId)
        viewModel.getProfileData()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(viewState.profileList.size) {it ->
                val item = viewState.profileList[it]
                if(it >= viewState.profileList.size -1 && !viewState.endReached && !viewState.isLoading) {
                    Log.e("TAG", "ProfilePageScreen: "+viewState.profileList.size )
                    viewModel.getProfileData()
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp)
                        .background(Color.Red)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = item.name.toString()
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = item.id.toString()
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = item.country.toString()
                    )
                }
            }
        }

        if(viewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }


}