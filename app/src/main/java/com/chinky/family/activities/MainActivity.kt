package com.chinky.family.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.composable.activities.ComposableMenuActivity
import com.chinky.family.services.activities.ServiceMenuActivity
import com.chinky.family.stateManagements.activities.StateManagementDemoActivity
import com.chinky.family.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayApplicationMenu(padding)
                }
            }
        }
    }

    @Composable
    fun DisplayApplicationMenu(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Button(onClick = {
                    val intent = Intent(this@MainActivity, ComposableMenuActivity::class.java)
                    startActivity(intent)
                }) {
                    Text("Composables")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {
                    val intent = Intent(this@MainActivity, StateManagementDemoActivity::class.java)
                    startActivity(intent)
                }) {
                    Text("State Management")
                }
            }
            Row {
                Button(onClick = {
                    val intent = Intent(this@MainActivity, ServiceMenuActivity::class.java)
                    startActivity(intent)
                }) {
                    Text("Services")
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }


}