package com.chinky.family.presentation.ui.security

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class EncryptionDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold { paddingValues ->
                    EncryptionScreen(paddingValues)
                }
            }
        }
    }

    @Composable
    private fun EncryptionScreen(paddingValues: PaddingValues) {
        var encryptionString by remember { mutableStateOf("") }
        Column(modifier = Modifier.padding(paddingValues).fillMaxWidth()) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text("Encrypting String")
                Spacer(modifier = Modifier.height(20.dp))
                TextField(value = encryptionString, onValueChange = {
                    encryptionString = it
                })
            }
        }
    }
}