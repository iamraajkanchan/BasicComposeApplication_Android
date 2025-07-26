package com.chinky.family.presentation.ui.security

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class SecurityMenuActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold { paddingValues ->
                    DisplayEncryptionMenu(paddingValues)
                }
            }
        }
    }

    @Composable
    private fun DisplayEncryptionMenu(paddingValues: PaddingValues) {
        Column {
            Button(onClick = {

            }) { Text("Encryption") }
        }
    }

}