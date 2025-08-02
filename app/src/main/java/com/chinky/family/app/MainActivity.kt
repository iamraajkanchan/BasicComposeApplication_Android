package com.chinky.family.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.activityLifecycle.ActivityLifecycleInComposeDemo
import com.chinky.family.presentation.ui.composableFunctions.activities.ComposableMenuActivity
import com.chinky.family.presentation.ui.locations.LocationInfoDemoActivity
import com.chinky.family.presentation.ui.navigationDemo.NavigationWithObjectArgumentActivity
import com.chinky.family.presentation.ui.navigationDemo.NavigationWithStringArgumentActivity
import com.chinky.family.presentation.ui.networkCall.NetworkCallDemoActivity
import com.chinky.family.presentation.ui.security.SecurityMenuActivity
import com.chinky.family.presentation.ui.services.activities.ServiceMenuActivity
import com.chinky.family.presentation.ui.stateManagements.activities.StateManagementDemoActivity
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayApplicationMenu(padding)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DisplayApplicationMenu(padding: PaddingValues = PaddingValues(16.dp)) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, ComposableMenuActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Composable Functions")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, StateManagementDemoActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("State Management")
            }
            Button(
                onClick = {
                    val intent = Intent(context, ServiceMenuActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Services")
            }
            Button(
                onClick = {
                    val intent = Intent(context, NetworkCallDemoActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Network Call")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, NavigationWithStringArgumentActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Navigation Demo with String")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, NavigationWithObjectArgumentActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Navigation Demo with Object")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, ActivityLifecycleInComposeDemo::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Activity Lifecycle in Compose")
            }
            Button(onClick = {
                val intent = Intent(context, LocationInfoDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Location Into")
            }
            Button(onClick = {
                val intent = Intent(context, SecurityMenuActivity::class.java)
                startActivity(intent)
            }) {
                Text("Security Demo")
            }
        }
    }


}