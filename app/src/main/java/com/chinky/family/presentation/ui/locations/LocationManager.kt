package com.chinky.family.presentation.ui.locations

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

class LocationManager(private val context: Context) {
    private val _state = mutableStateOf<LocationState>(LocationState.Loading)
    val state: LocationState by _state

    fun requestLocation() {
        if (!hasLocationPermissions()) {
            _state.value = LocationState.Error("Location permission is not granted")
            return
        }

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        try {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let { lastLocation ->
                        _state.value = LocationState.Success(
                            lastLocation.latitude,
                            lastLocation.longitude
                        )
                    } ?: run {
                        _state.value = LocationState.Error("Location is null")
                    }
                }
                .addOnFailureListener {
                    _state.value = LocationState.Error("Failed to get location")
                }
        } catch (e: SecurityException) {
            _state.value = LocationState.Error("Location permission is not granted")
        }
    }

    fun hasLocationPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this.context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this.context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }
}