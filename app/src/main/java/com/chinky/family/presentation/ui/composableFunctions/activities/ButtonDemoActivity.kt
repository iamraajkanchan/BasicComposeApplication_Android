package com.chinky.family.presentation.ui.composableFunctions.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class ButtonDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousButtonOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousButtonOptions(padding: PaddingValues) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                Toast.makeText(context, "Default Button Clicked", Toast.LENGTH_SHORT).show()
            }) { Text("Default Button") }
            Button(
                onClick = {
                    Toast.makeText(context, "Modified Button Clicked", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ApplicationColor.Orange,
                    contentColor = ApplicationColor.White
                ),
            ) { Text("Modified UI Button") }
            Button(
                onClick = {
                    Toast.makeText(context, "Bordered Button Clicked", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ApplicationColor.Orange,
                    contentColor = ApplicationColor.White
                ),
                border = BorderStroke(color = ApplicationColor.White, width = 1.dp)
            ) { Text("Bordered Button") }
        }
    }
}