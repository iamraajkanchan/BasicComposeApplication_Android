package com.chinky.family.presentation.ui.common

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable

@Composable
fun <C> AppActivityButton(componentActivity: ComponentActivity ,klass: Class<C>, content: @Composable () -> Unit) {
    Button(onClick = {
        val activityIntent = Intent(componentActivity, klass)
        componentActivity.startActivity(activityIntent)
    }) {
        content()
    }
}