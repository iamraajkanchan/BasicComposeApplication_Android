package com.chinky.family

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.activities.AlertDialogDemoActivity
import com.chinky.family.activities.BoxDemoActivity
import com.chinky.family.activities.ButtonDemoActivity
import com.chinky.family.activities.CardDemoActivity
import com.chinky.family.activities.CheckboxDemoActivity
import com.chinky.family.activities.CircularProgressIndicatorDemoActivity
import com.chinky.family.activities.TextDemoActivity
import com.chinky.family.activities.TextFieldDemoActivity
import com.chinky.family.ui.theme.ApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayAllComposeMenu(padding)
                }
            }
        }
    }

    @Composable
    fun DisplayAllComposeMenu(padding: PaddingValues) {
        val buttonWidth = 175.dp
        val leftButtonModifier = Modifier
            .padding(0.dp, 0.dp, 4.dp, 0.dp)
            .width(buttonWidth)
        val rightButtonModifier = Modifier
            .padding(4.dp, 0.dp, 0.dp, 0.dp)
            .width(buttonWidth)
        Column(
            modifier = Modifier
                .fillMaxSize() // Ensure full width usage
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally // Align children horizontally
        ) {
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, TextDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Text Composable")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, TextFieldDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("TextField Composable")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, AlertDialogDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Alert Dialog Composable")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, BoxDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Box Composable")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, ButtonDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Button Composable")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, CardDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Card Composable")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, CheckboxDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Checkbox Composable")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent = Intent(
                            this@MainActivity,
                            CircularProgressIndicatorDemoActivity::class.java
                        )
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Circular Progress Indicator Composable")
                }
            }
        }
    }
}