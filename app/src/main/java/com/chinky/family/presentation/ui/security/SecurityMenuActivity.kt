package com.chinky.family.presentation.ui.security

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
        val context = LocalContext.current
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues),horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                val intent = Intent(context, EncryptionDemoActivity::class.java)
                startActivity(intent)
            }) { Text("AES Encryption/Decryption") }
        }
    }

}