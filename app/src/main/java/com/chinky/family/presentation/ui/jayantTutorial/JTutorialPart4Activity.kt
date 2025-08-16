package com.chinky.family.presentation.ui.jayantTutorial

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import com.chinky.family.presentation.ui.common.AppTopBar
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

/**
 * In this tutorial we are going to learn, how to define a Top
 * */
class JTutorialPart4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(topBar = {
                    AppTopBar("Jayant Part 4 Tutorial", this)
                })
                {paddingValues ->
                    ShowLoginScreen(paddingValues)
                }
            }
        }
    }
    @Composable
    fun ShowLoginScreen(paddingValues: PaddingValues) {
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val usernameFocusRequester = remember { FocusRequester() }
        val usernameBorderColor = remember { mutableStateOf(ApplicationColor.Orange) }
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Username") },
                modifier = Modifier.focusRequester(usernameFocusRequester)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") }
            )
            OutlinedButton(onClick = {login(username, password, usernameFocusRequester,context)}) { Text("Login") }
        }
    }

    private fun login(username: MutableState<String>, password: MutableState<String>, usernameFocusRequester : FocusRequester ,context: Context) {
        if (username.value.isNotEmpty() && password.value.isNotEmpty()) {
            if (username.value == "chinky" && password.value.equals("minky")) {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                username.value = ""
                password.value = ""
                usernameFocusRequester.requestFocus()
            } else {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter username and password", Toast.LENGTH_SHORT).show()
        }
    }
}