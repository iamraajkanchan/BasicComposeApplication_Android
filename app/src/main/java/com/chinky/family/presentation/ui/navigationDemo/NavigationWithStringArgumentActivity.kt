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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class NavigationWithStringArgumentActivity : ComponentActivity() {
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
                UserInfoScreen(padding) { userEmail, userName ->
                    navController.navigate("user_about/$userEmail/$userName")
                }
            }
            composable(route = "user_about/{userEmail}/{userName}", arguments = listOf(
                navArgument("userEmail") {
                    type = NavType.StringType
                },
                navArgument("userName") {
                    type = NavType.StringType
                }
            )) {
                val userName = it.arguments?.getString("userName")
                val email = it.arguments?.getString("userEmail")
                UserAboutScreen(padding, userName!!, email!!)
            }
        }
    }

    @Composable
    fun UserInfoScreen(padding: PaddingValues, onClick: (userEmail: String, userName: String) -> Unit) {
        var userEmail by rememberSaveable { mutableStateOf("") }
        var userName by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("UserName")
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = userName, onValueChange = {
                userName = it
            })
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
                    onClick(userEmail, userName)
                }
            ) {
                Text("Get Detail")
            }
        }
    }

    @Composable
    fun UserAboutScreen(padding: PaddingValues, userName: String, email: String) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hello $userName your email address is $email!")
        }
    }


}