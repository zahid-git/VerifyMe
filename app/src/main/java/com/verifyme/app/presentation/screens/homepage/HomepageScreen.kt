package com.verifyme.app.presentation.screens.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.verifyme.app.R
import com.verifyme.app.navigation.NavRoutes
import com.verifyme.app.presentation.theme.AppCommonColor
import com.verifyme.app.presentation.theme.VerifyMeTheme

@Composable
fun HomepageScreen(
    navController: NavHostController
) {
    ShowHomePage(modifier = Modifier.padding(start = 20.dp, end = 20.dp), navController)
}


@Composable
fun ShowHomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var viewModel: HomepageViewModel = hiltViewModel()

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val homepageViewState by viewModel.homepageViewState.collectAsStateWithLifecycle()


    LaunchedEffect(Unit) {
        viewModel.getStoreInformation()
    }



    VerifyMeTheme {

        /*Scaffold(
            bottomBar = {
                NavigationBar() {
                    NavigationBarItem(
                        selectedItemIndex == 0,
                        onClick = {
                            selectedItemIndex = 0
                        },
                        label = { Text(text = "Home") },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = "Home")
                                }
                            }) {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(R.drawable.ic_dollar),
                                    contentDescription = "Home"
                                )
                            }
                        })
                    NavigationBarItem(
                        selectedItemIndex == 1,
                        onClick = {
                            selectedItemIndex = 1
                        },
                        label = { Text(text = "Home") },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = "Home")
                                }
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_dollar),
                                    contentDescription = "Home"
                                )
                            }
                        })
                    NavigationBarItem(
                        selectedItemIndex == 2,
                        onClick = {
                            selectedItemIndex = 2
                        },
                        label = { Text(text = "Home") },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = "Home")
                                }
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_dollar),
                                    contentDescription = "Home"
                                )
                            }
                        })
                    NavigationBarItem(
                        selectedItemIndex == 3,
                        onClick = {
                            selectedItemIndex = 3
                        },
                        label = { Text(text = "Home") },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = "Home")
                                }
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_dollar),
                                    contentDescription = "Home"
                                )
                            }
                        })
                    NavigationBarItem(
                        selectedItemIndex == 4,
                        onClick = {
                            selectedItemIndex = 4
                        },
                        label = { Text(text = "Home") },
                        icon = {
                            BadgedBox(badge = {
                                Badge() {
                                    Text(text = "Home")
                                }
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_dollar),
                                    contentDescription = "Home"
                                )
                            }
                        })
                }
            },
            content = {

            }
        )*/
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(AppCommonColor.BackgroundColor),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                // Toolbar
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                        .height(80.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Hi, Md. Zahid!"
                    )
                    Text(
                        text = "Good Evening"
                    )
                }

                // Summary Section
                LazyColumn(
                    modifier = Modifier
                        .wrapContentHeight()
                        .background(Color.Transparent)
                ) {
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            homepageViewState.storeInformation?.let { storeDetails ->
                                item {
                                    HomePageSummaryView(
                                        baseModifier = Modifier.padding(start = 5.dp),
                                        imageId = R.drawable.ic_attempt,
                                        title = "Attempt",
                                        value = storeDetails.totalAttempt.toString()
                                    )
                                }
                                item {
                                    HomePageSummaryView(
                                        baseModifier = Modifier.padding(start = 5.dp),
                                        imageId = R.drawable.ic_ok_sign,
                                        title = "Verified",
                                        value = storeDetails.totalVerified.toString()
                                    )
                                }
                                item {
                                    HomePageSummaryView(
                                        baseModifier = Modifier.padding(start = 5.dp),
                                        imageId = R.drawable.ic_due_bill,
                                        title = "Pending",
                                        value = "$ "+storeDetails.totalPending.toString()
                                    )
                                }
                                item {
                                    HomePageSummaryView(
                                        baseModifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                        imageId = R.drawable.ic_dollar,
                                        title = "Paid",
                                        value = "$ "+storeDetails.totalPaid.toString()
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Bottom Section
            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigate(NavRoutes.SplashScreen)
                    },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Box {
                        Text(
                            modifier = Modifier.wrapContentWidth().align(Alignment.Center),
                            text = "Check Profiles",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomePageSummaryView(
    baseModifier: Modifier,
    imageId: Int,
    title: String,
    value: String
) {
    Card(
        modifier = baseModifier
            .padding(bottom = 20.dp, top = 20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Card(
                modifier = Modifier
                    .size(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = AppCommonColor.attemptColor,
                )
            ) {
                Image(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(50.dp),
                    painter = painterResource(imageId),
                    contentDescription = "",
                )
            }
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                fontSize = 25.sp,
                color = AppCommonColor.darkBlackTextColor,
                fontWeight = FontWeight.Bold,
                text = title
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                color = AppCommonColor.blackTextColor,
                text = value
            )
        }
    }
}