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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chinky.family.presentation.ui.common.AppTopBar
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import dagger.hilt.android.internal.Contexts

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
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            OutlinedButton(onClick = {login(username, password, context)}) { Text("Login") }
        }
    }

    private fun login(username: String, password: String, context: Context) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (username == "chinky" && password.equals("minky")) {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please enter username and password", Toast.LENGTH_SHORT).show()
        }
    }
}