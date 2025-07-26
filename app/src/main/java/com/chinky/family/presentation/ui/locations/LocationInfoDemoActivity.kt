package com.chinky.family.presentation.ui.locations

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.chinky.family.domain.utils.LocationManager
import com.chinky.family.domain.state.LocationState
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class LocationInfoDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold { paddingValues ->
                    DisplayLocationInfo(paddingValues)
                }
            }
        }
    }


    @Composable
    private fun DisplayLocationInfo(padding: PaddingValues) {
        val context = LocalContext.current
        val locationState = rememberLocationState(context)
        LocationPermissionHandler(
            onPermissionGranted = { locationState.requestLocation() }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = locationState.state) {
                is LocationState.Loading -> {
                    // You might want to show a loading indicator here
                    CircularProgressIndicator()
                }

               is LocationState.Success -> {
                    DisplayLocationSuccessResult(
                        latitude = currentState.latitude,
                        longitude = currentState.longitude
                    )
                }

               is LocationState.Error -> {
                    DisplayLocationFailedResult(currentState.message)
                }
            }
        }
    }

    @Composable
    private fun LocationPermissionHandler(
        onPermissionGranted: () -> Unit
    ) {
        val context = LocalContext.current
        val permissions = getLocationPermissions()
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionContracts ->
            if (permissionContracts.values.all { it }) {
                onPermissionGranted()
            }
        }

        LaunchedEffect(Unit) {
            if (LocationManager(context).hasLocationPermissions()) {
                onPermissionGranted()
            } else {
                launcher.launch(permissions)
            }
        }
    }

    @Composable
    private fun rememberLocationState(context: Context): LocationManager {
        return remember { LocationManager(context) }
    }

    private fun getLocationPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        } else {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    @Composable
    private fun DisplayLocationSuccessResult(longitude: Double, latitude: Double) {
        Column {
            Text("Longitude: $longitude")
            Text("Latitude: $latitude")
        }
    }

    @Composable
    private fun DisplayLocationFailedResult(error: String) {
        Text("$error!")
    }
}