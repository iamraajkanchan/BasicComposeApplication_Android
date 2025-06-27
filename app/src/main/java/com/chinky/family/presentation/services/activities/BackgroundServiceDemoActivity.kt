package com.chinky.family.presentation.services.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.chinky.family.presentation.services.services.DemoBackgroundService
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import com.chinky.family.presentation.utils.ApplicationConstant

class BackgroundServiceDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayUI(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayUI(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                val intent =
                    Intent(this@BackgroundServiceDemoActivity, DemoBackgroundService::class.java)
                intent.putExtra(
                    ApplicationConstant.ARGUMENT_DEMO_BACKGROUND_SERVICE_MAIN_DATA,
                    "Hello from activity!"
                )
                startService(intent)
            }) { Text("Start Service") }
        }
    }
}