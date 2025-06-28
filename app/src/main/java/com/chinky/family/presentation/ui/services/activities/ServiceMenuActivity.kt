package com.chinky.family.presentation.ui.services.activities

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
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class ServiceMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayServiceMenu(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayServiceMenu(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                val intent = Intent(this@ServiceMenuActivity, BoundServiceDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Bound Service")
            }
            Button(onClick = {
                val intent = Intent(this@ServiceMenuActivity, BackgroundServiceDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Background Service")
            }
        }
    }
}