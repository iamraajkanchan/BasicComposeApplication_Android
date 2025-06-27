package com.chinky.family.presentation.composableFunctions.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class TextButtonDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousTextButtonOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousTextButtonOptions(padding: PaddingValues) {
        val context = LocalContext.current
        val spacing = 20.dp
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing))
            BasicTextButton(context)
            Spacer(modifier = Modifier.height(spacing))
            ModifiedTextButton(context)
        }
    }

    @Composable
    private fun BasicTextButton(context: Context) {
        TextButton(onClick = {
            Toast.makeText(context, "Clicked Basic Text Button", Toast.LENGTH_SHORT).show()
        }) { Text("Basic Text Button") }
    }

    @Composable
    private fun ModifiedTextButton(context: Context) {
        TextButton(onClick = {
            Toast.makeText(context, "Clicked Modified Basic Text Button", Toast.LENGTH_SHORT).show()
        }) { Text("Basic Text Button", color = ApplicationColor.Orange) }
    }
}