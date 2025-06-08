package com.chinky.family.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class TextDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    displayVariousTextOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun displayVariousTextOptions(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // You don't need a comma to add another composable
            Text("Just a Normal Text with no modifier")
            Text(
                "Just a Normal Text with background and padding",
                modifier = Modifier.background(ApplicationColor.PurpleGrey80).padding(8.dp)

            )
            Text("Just a Normal Text with no modifier")
        }
    }
}