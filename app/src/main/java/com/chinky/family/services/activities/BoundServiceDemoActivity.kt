package com.chinky.family.services.activities

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chinky.family.services.services.DemoBoundService
import com.chinky.family.ui.theme.ApplicationTheme

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
        var valueFromService = remember { mutableStateOf<String?>(null) }
        val demoBoundService = remember { mutableStateOf<DemoBoundService?>(null) }
        val demoBoundServiceConnection = remember {
            object : ServiceConnection {
                override fun onServiceConnected(component: ComponentName?, binder: IBinder?) {
                    demoBoundService.value =
                        (binder as DemoBoundService.DemoBoundServiceBinder).getDemoBoundService()
                }

                override fun onServiceDisconnected(p0: ComponentName?) {
                    demoBoundService.value = null
                }
            }
        }

        DisposableEffect(Unit) {
            val intent = Intent(context, DemoBoundService::class.java)
            context.bindService(intent, demoBoundServiceConnection, BIND_AUTO_CREATE)
            demoBoundService.value?.setActivityData("Hello from activity!")
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
                valueFromService = mutableStateOf(demoBoundService.value?.getActivityData())
            }) {
                Text("Fetch Data from Demo Bound Service")
            }
            Text(text = valueFromService.value ?: "Service connection Error!")
        }
    }
}