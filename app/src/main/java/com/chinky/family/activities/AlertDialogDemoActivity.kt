package com.chinky.family.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class AlertDialogDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousAlertDialogOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousAlertDialogOptions(padding: PaddingValues) {
        val isShowingBasicAlertDialog = remember { mutableStateOf(false) }
        val isShowingCustomAlertDialog = remember { mutableStateOf(false) }
        val isShowingTermAlertDialog = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    isShowingBasicAlertDialog.value = !isShowingBasicAlertDialog.value
                }) {
                Text("Basic Alert Dialog")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    isShowingCustomAlertDialog.value = !isShowingCustomAlertDialog.value
                }) {
                Text("Custom Alert Dialog")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    isShowingTermAlertDialog.value = !isShowingTermAlertDialog.value
                }) {
                Text("Term Alert Dialog")
            }
        }
        if (isShowingBasicAlertDialog.value) {
            BasicAlertDialog {
                isShowingBasicAlertDialog.value = false
            }
        }
        if (isShowingCustomAlertDialog.value) {
            CustomAlertDialog {
                isShowingCustomAlertDialog.value = false
            }
        }
        if (isShowingTermAlertDialog.value) {
            TermAlertDialog {
                isShowingTermAlertDialog.value = false
            }
        }
    }


    @Composable
    private fun BasicAlertDialog(onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Basic Alert Dialog") },
            text = { Text("This is a basic alert dialog!") },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }

    @Composable
    private fun CustomAlertDialog(onDismiss: () -> Unit) {
        val context = LocalContext.current
        val userNameText = remember { mutableStateOf("") }
        val userNameTextFocusRequester = remember { FocusRequester() }
        val submitFocusRequester = remember { FocusRequester() }
        AlertDialog(
            modifier = Modifier
                .border(
                    BorderStroke(2.dp, ApplicationColor.Purple80),
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            onDismissRequest = onDismiss,
            title = { Text("Enter Your Name") },
            text = {
                Column {
                    Icon(
                        imageVector = Icons.TwoTone.Face,
                        contentDescription = "User Profile",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = userNameText.value,
                        onValueChange = { newText ->
                            userNameText.value = newText
                        },
                        label = { Text("Enter your name") },
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                userNameTextFocusRequester.freeFocus()
                                submitFocusRequester.requestFocus()
                            }
                        ),
                        modifier = Modifier
                            .focusRequester(userNameTextFocusRequester)
                            .background(ApplicationColor.Green, shape = RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp)),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        if (userNameText.value.isEmpty()) {
                            Toast.makeText(context, "Name can't be empty!", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "User name submitted!", Toast.LENGTH_SHORT)
                                .show()
                            onDismiss()
                        }
                    }) {
                        Text("Submit")
                    }
                }
            },
            confirmButton = { },
            dismissButton = { }
        )
        LaunchedEffect(Unit) {
            userNameTextFocusRequester.requestFocus()
        }
    }

    @Composable
    private fun TermAlertDialog(onDismiss: () -> Unit) {
        val agreed = remember { mutableStateOf(false) }
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Term Alert Dialog") },
            text = {
                Column {
                    Text("Please agree to the terms to continue!")
                    Row {
                        Checkbox(checked = agreed.value, onCheckedChange = { agreed.value = it })
                        Text("Yes, I agree", modifier = Modifier.padding(0.dp, 15.dp))
                    }
                }
            },
            confirmButton = {
                Button(onClick = onDismiss) {
                    Text("Confirm")
                }
            },
            dismissButton = { Button(onClick = onDismiss) { Text("Cancel") } }
        )
    }


    @Composable
    fun CallbackAlertDialog(
        showDialog: Boolean,
        onDismiss: () -> Unit,
        onConfirm: () -> Unit // 👈 Callback when user confirms
    ) {
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { onDismiss() },
                title = { Text("Confirm Action") },
                text = { Text("Are you sure you want to proceed?") },
                confirmButton = {
                    Button(onClick = {
                        onConfirm() // 👈 Call the action
                        onDismiss() // 👈 Close dialog after confirming
                    }) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(onClick = { onDismiss() }) {
                        Text("No")
                    }
                }
            )
        }
    }

    @Composable
    fun ShowCallbackDialogExample(showDialog: Boolean) {
        val context = LocalContext.current
        var _showDialog = showDialog;
        Column {
            Button(onClick = { _showDialog = true }) {
                Text("Show Alert Dialog")
            }

            CallbackAlertDialog(
                showDialog = showDialog,
                onDismiss = { _showDialog = false },
                onConfirm = {
                    Toast.makeText(
                        context,
                        "Action Confirmed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
}