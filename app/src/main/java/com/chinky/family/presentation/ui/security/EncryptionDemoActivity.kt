package com.chinky.family.presentation.ui.security

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import com.chinky.family.presentation.viewModels.AESEncryptionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EncryptionDemoActivity : ComponentActivity() {
    val viewModel by viewModels<AESEncryptionViewModel>()
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
        var decryptedString by remember { mutableStateOf("Evaluation String") }
        var encryptedByteArray by remember { mutableStateOf(byteArrayOf()) }
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = encryptionString, onValueChange = {
                encryptionString = it
            })
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(onClick = {
                    lifecycleScope.launch {
                        encryptedByteArray = viewModel.encryptStringUsingAES(encryptionString, "ChinkyMinky")
                        decryptedString = String(encryptedByteArray, Charsets.UTF_8)
                    }
                }) {
                    Text("Encrypt")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(onClick = {
                    lifecycleScope.launch {
                        decryptedString = viewModel.decryptStringUsingAES(encryptedByteArray, "ChinkyMinky")
                    }
                }) {
                    Text("Decrypt")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = decryptedString)
        }
    }
}