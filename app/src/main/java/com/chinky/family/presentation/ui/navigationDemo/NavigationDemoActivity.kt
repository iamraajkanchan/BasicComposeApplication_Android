package com.chinky.family.presentation.ui.navigationDemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class NavigationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold() {
                UserScreen(it)
            }
        }
    }

    @Composable
    fun UserScreen(padding: PaddingValues) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "user_info") {
            composable(route = "user_info") {
                UserInfoScreen(padding) {
                    navController.navigate("user_about/$it")
                }
            }
            composable(route = "user_about/{email}", arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )) {
                val email = it.arguments?.getString("email")
                UserAboutScreen(padding, email!!)
            }
        }
    }

    @Composable
    fun UserInfoScreen(padding: PaddingValues, onClick: (email: String) -> Unit) {
        var userEmail by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Email")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = userEmail,
                onValueChange = {
                    userEmail = it
                })
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    onClick(userEmail)
                }
            ) {
                Text("Get Detail")
            }
        }
    }

    @Composable
    fun UserAboutScreen(padding: PaddingValues, email: String) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello $email!")
        }
    }


}