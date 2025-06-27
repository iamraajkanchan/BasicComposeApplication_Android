package com.chinky.family.presentation.services.activities

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
// Todo Application Notes: If by remember is not working, use the following import method
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chinky.family.presentation.services.services.DemoBoundService
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class BoundServiceDemoActivity : ComponentActivity() {
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
        val context = LocalContext.current
        var valueFromService by remember { mutableStateOf<String?>(null) }
        var demoBoundService by remember { mutableStateOf<DemoBoundService?>(null) }
        var fetchDataCount by remember { mutableIntStateOf(0) }
        val demoBoundServiceConnection = remember {
            object : ServiceConnection {
                override fun onServiceConnected(component: ComponentName?, binder: IBinder?) {
                    demoBoundService =
                        (binder as DemoBoundService.DemoBoundServiceBinder).getDemoBoundService()
                   demoBoundService?.setActivityData("${fetchDataCount++} : Hello from activity!") // Move here
                }

                override fun onServiceDisconnected(p0: ComponentName?) {
                    demoBoundService = null
                }
            }
        }

        DisposableEffect(Unit) {
            val intent = Intent(context, DemoBoundService::class.java)
            context.bindService(intent, demoBoundServiceConnection, BIND_AUTO_CREATE)

            onDispose {
                context.unbindService(demoBoundServiceConnection)
            }
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                demoBoundService?.setActivityData("${fetchDataCount++} : Hello from activity!")
                valueFromService = demoBoundService?.getActivityData() // Correct update
            }) {
                Text("Fetch Data from Demo Bound Service")
            }
            Text(text = valueFromService ?: "Please connect!")
        }
    }
}