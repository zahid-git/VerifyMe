@file:OptIn(ExperimentalMaterial3Api::class)

package com.verifyme.app.presentation.screens.profilepage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.verifyme.app.R
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
        //viewModel.getProfileData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        PullToRefreshBox(
            state = rememberPullToRefreshState(),
            isRefreshing = viewState.isLoading,
            onRefresh = {
                viewModel.getProfileData()
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(viewState.profileList.size) { it ->
                    val item = viewState.profileList[it]
                    if (it >= viewState.profileList.size - 1 && !viewState.endReached && !viewState.isLoading) {
                        Log.e("TAG", "ProfilePageScreen: " + viewState.profileList.size)
                        viewModel.getProfileData()
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .wrapContentHeight()
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
                        item.selfieImage?.let {
                            AsyncImage(
                                modifier = Modifier.size(100.dp),
                                model = it.selfieImageLink.toString(),
                                contentDescription = "Selfie Image",
                                placeholder = painterResource(R.drawable.ic_placeholder),
                                error = painterResource(R.drawable.ic_profile)
                            )
                        }
                    }
                }
            }

            /*if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                )
            }*/
        }
    }


}