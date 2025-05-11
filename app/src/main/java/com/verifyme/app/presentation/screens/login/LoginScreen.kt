package com.verifyme.app.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.verifyme.app.R
import com.verifyme.app.presentation.theme.AppCommonColor
import com.verifyme.app.presentation.theme.VerifyMeTheme


@Composable
fun LoginPageScreen(
    navController: NavHostController
) {


    ShowLoginPage(modifier = Modifier)

}

@Preview
@Composable
fun ShowLoginPage(
    modifier: Modifier = Modifier
) {

    var viewModel: LoginViewModel = hiltViewModel()
    val loginViewState by viewModel.loginViewState.collectAsStateWithLifecycle()

    VerifyMeTheme {

        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            Row (
                modifier = modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = modifier
                        .padding(20.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            color = MaterialTheme.colorScheme.secondary
                        )
                        .padding(bottom = 50.dp),
                ) {
                    Image(
                        modifier = modifier
                            .height(150.dp)
                            .width(150.dp)
                            .align(Alignment.CenterHorizontally),
                        imageVector = ImageVector.vectorResource(R.drawable.verifyme_logo),
                        contentDescription = "logo"
                    )
                    Text(
                        modifier = modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.sign_in_to_continue),
                        fontSize = 22.sp
                    )

                    TextField(
                        modifier = modifier
                            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                            .border(
                                width = 1.dp,
                                color = AppCommonColor.lightBlackColor,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = ""
                            )
                        },
                        label = {
                            Text(text = stringResource(R.string.enter_your_email_address))
                        },
                        maxLines = 1,
                        value = loginViewState.emailAddress,
                        shape = RoundedCornerShape(5.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = AppCommonColor.primaryLightColor,
                            unfocusedContainerColor = AppCommonColor.primaryLightColor,
                            disabledContainerColor = AppCommonColor.primaryLightColor,
                            errorContainerColor = AppCommonColor.primaryLightColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        onValueChange = {
                            viewModel.onEmailAddressChange(it)
                        },
                    )

                    TextField(
                        modifier = modifier
                            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = AppCommonColor.lightBlackColor,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = ""
                            )
                        },
                        maxLines = 1,
                        label = {
                            Text(text = stringResource(R.string.enter_your_password))
                        },
                        value = loginViewState.password,
                        shape = RoundedCornerShape(5.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = AppCommonColor.primaryLightColor,
                            unfocusedContainerColor = AppCommonColor.primaryLightColor,
                            disabledContainerColor = AppCommonColor.primaryLightColor,
                            errorContainerColor = AppCommonColor.primaryLightColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        onValueChange = {
                            viewModel.onPasswordChange(it)
                        },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Button(
                        modifier = modifier
                            .padding(top = 40.dp, start = 20.dp, end = 20.dp)
                            .fillMaxWidth(),
                        onClick = {
                            viewModel.doLogin()
                        },
                        contentPadding = PaddingValues(15.dp),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.do_continue),
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}