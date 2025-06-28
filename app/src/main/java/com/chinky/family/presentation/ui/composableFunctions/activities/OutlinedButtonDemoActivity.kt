package com.chinky.family.presentation.ui.composableFunctions.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class OutlinedButtonDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousOutLinedButton(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousOutLinedButton(padding: PaddingValues) {
        val context = LocalContext.current
        val spacing = 10.dp
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing))
            BasicOutLinedButton(context)
            Spacer(modifier = Modifier.height(spacing))
            ModifiedOutlinedButton(context)
        }
    }


    @Composable
    private fun BasicOutLinedButton(context: Context) {
        OutlinedButton(onClick = {
            Toast.makeText(context, "You Clicked Basic Outlined Button!", Toast.LENGTH_SHORT).show()
        }) {
            Text("Basic Outlined Button")
        }
    }

    @Composable
    private fun ModifiedOutlinedButton(context: Context) {
        OutlinedButton(
            onClick = {
                Toast.makeText(context, "You Clicked Modified Outlined Button!", Toast.LENGTH_SHORT)
                    .show()
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ApplicationColor.Black,
                contentColor = ApplicationColor.White
            ),
            border = BorderStroke(width = 1.dp, color = ApplicationColor.Orange)
        ) {
            Text("Modified Outlined Button")
        }
    }
}