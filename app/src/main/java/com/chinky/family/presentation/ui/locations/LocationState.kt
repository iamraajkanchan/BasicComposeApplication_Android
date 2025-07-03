package com.chinky.family.presentation.ui.locations

sealed class LocationState {
    data object Loading : LocationState()
    data class Success(val latitude: Double, val longitude: Double) : LocationState()
    data class Error(val message: String) : LocationState()
}