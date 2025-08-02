package com.chinky.family.presentation.ui.permissionsDemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chinky.family.R
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class PermissionsMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold {
                    paddingValues ->
                    PermissionsMenuScreen(paddingValues)
                }
            }
        }
    }

    @Composable
    fun PermissionsMenuScreen(paddingValues: PaddingValues) {
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                val intent = Intent(this@PermissionsMenuActivity, CameraAndStoragePermissionActivity::class.java)
                startActivity(intent)
            }) {
                Text("Camera && Storage Permission")
            }
        }
    }
}