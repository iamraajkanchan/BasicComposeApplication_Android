package com.chinky.family.presentation.ui.common

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(appTitle: String, componentActivity: ComponentActivity) {
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text =appTitle,
        )
    },
        navigationIcon = {
            IconButton(onClick = {
                componentActivity.onBackPressedDispatcher.onBackPressed()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}