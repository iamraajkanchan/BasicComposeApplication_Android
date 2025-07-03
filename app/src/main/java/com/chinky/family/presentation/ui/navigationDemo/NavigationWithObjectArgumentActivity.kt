package com.chinky.family.presentation.ui.navigationDemo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chinky.family.domain.model.User
import com.google.gson.Gson

class NavigationWithObjectArgumentActivity : ComponentActivity() {

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
                UserInfoScreen(padding) { userObjectString ->
                    navController.navigate("user_about/$userObjectString")
                }
            }
            composable(
                route = "user_about/{userObjectString}", arguments = listOf(
                    navArgument("userObjectString") {
                        type = NavType.StringType
                    },
                )
            ) {
                val userObjectString = it.arguments?.getString("userObjectString")
                val user = Gson().fromJson(userObjectString, User::class.java)
                UserAboutScreen(padding, user!!)
            }
        }
    }

    @Composable
    fun UserInfoScreen(padding: PaddingValues, onClick: (userObjectString: String) -> Unit) {
        var userEmail by rememberSaveable { mutableStateOf("") }
        var userName by rememberSaveable { mutableStateOf("") }
        var userPhone by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Create a Box to control the alignment of Text relative to TextField
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        "UserName",
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(value = userName, onValueChange = {
                        userName = it
                    })
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        "Email",
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        value = userEmail,
                        onValueChange = {
                            userEmail = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        "Phone",
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    TextField(
                        value = userPhone,
                        onValueChange = {
                            userPhone = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    val userObject = User(1, userName, userEmail, userPhone)
                    val userObjectString = Uri.encode(Gson().toJson(userObject))
                    onClick(userObjectString)
                }
            ) {
                Text("Get Detail")
            }
        }
    }


    @Preview
    @Composable
    fun UserAboutScreen(padding: PaddingValues, user: User) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(20.dp)) {
                Text("Hello ${user.name} your email address is ${user.email} and your phone number is ${user.phone}!")
            }
        }
    }


}