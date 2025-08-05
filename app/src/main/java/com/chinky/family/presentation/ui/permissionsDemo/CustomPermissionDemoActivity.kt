package com.chinky.family.presentation.ui.permissionsDemo

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class CustomPermissionDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold {
                    paddingValues ->
                    CustomPermissionDemoScreen(paddingValues)
                }
            }
        }
    }

    @Composable
    fun CustomPermissionDemoScreen(paddingValues: PaddingValues) {
        val context = LocalContext.current
        val permissionGranted = remember { mutableStateOf(false) }
        val catPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission(), {
            permissionGranted.value = it
        })
        LaunchedEffect(Unit) {
            if (ActivityCompat.checkSelfPermission(context, "com.chinky.family.CAT_PERMISSION") != PackageManager.PERMISSION_GRANTED) {
                catPermissionLauncher.launch("com.chinky.family.CAT_PERMISSION")
            } else {
                permissionGranted.value = true
            }
        }
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            if (permissionGranted.value) {
                Text(text = "Permission Granted")
            } else {
                Text(text = "Permission Not Granted")
            }
        }
    }

}