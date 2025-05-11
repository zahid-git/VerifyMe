package com.verifyme.app.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.verifyme.app.presentation.screens.login.DialogData
import com.verifyme.app.presentation.theme.VerifyMeTheme


@Composable
fun CustomAlertDialog(
    dialogData: DialogData,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    if (dialogData.showDialog) {
        Dialog(
            onDismissRequest = { onCancel() }
        ) {
            CustomDialog(dialogData, onConfirm, onCancel)
        }


        /*(
            onDismissRequest = onDismiss,
            title = { Text(dialogData.title) },
            text = { Text(dialogData.message) },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )*/
    }
}

@Composable
fun CustomDialog(
    dialogData: DialogData = DialogData(),
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
) {
    VerifyMeTheme {
        Card() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                dialogData.topIcon?.let {
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .padding(20.dp),
                        painter = painterResource(dialogData.topIcon!!),
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF1A1A1A),
                    text = dialogData.title
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6C6C6C),
                    text = dialogData.message
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp, bottom = 20.dp, end = 20.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .weight(1f),
                        onClick = { onCancel() },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary),
                    ) {
                        Text(
                            text = dialogData.cancelBtnName,
                            color = Color.Black
                        )
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = {
                            onConfirm()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)

                    ) {
                        Text(
                            text = dialogData.successBtnName,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }

    }

}