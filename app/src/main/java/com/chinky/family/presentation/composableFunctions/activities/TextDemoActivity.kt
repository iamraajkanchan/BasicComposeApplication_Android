package com.chinky.family.presentation.composableFunctions.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationFont
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class TextDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousTextOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousTextOptions(padding: PaddingValues) {
        // Note: You can only find LocalContext.current in a composable and this is the only way to declare and define a context.
        // In a ComponentActivity, you won't find context in onCreate, onStart, onResume, onPause, onStop and onDestroy overridden methods.
        //
        val context = LocalContext.current
        // In a ComponentActivity, you won't find applicationContext in onCreate, onStart, onResume, onPause, onStop and onDestroy overridden methods.
        val applicationContext = LocalContext.current.applicationContext

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // You don't need a comma to add another composable
            Text("Normal Text with no modifier")
            Text(
                "Normal Text with background and padding",
                modifier = Modifier
                    .background(ApplicationColor.PurpleGrey80)
                    .padding(8.dp)

            )
            Text(
                "Normal Text with modified color and font size",
                color = ApplicationColor.Orange,
                fontSize = 16.sp
            )
            Text(
                "Normal Text with bold font and weight",
                color = ApplicationColor.Blue,
                fontSize = 16.sp,
                fontFamily = ApplicationFont.robotoBold,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Clickable Text with italic font",
                color = ApplicationColor.Green,
                fontSize = 16.sp,
                fontFamily = ApplicationFont.robotoItalic,
                modifier = Modifier.clickable {
                    Toast.makeText(context, "Clicked Green Text", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}