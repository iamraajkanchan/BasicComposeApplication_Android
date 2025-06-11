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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.chinky.family.activities.ColumnDemoActivity
import com.chinky.family.activities.DropDownMenuDemoActivity
import com.chinky.family.activities.IconButtonDemoActivity
import com.chinky.family.activities.IconDemoActivity
import com.chinky.family.activities.ImageDemoActivity
import com.chinky.family.activities.LazyColumnDemoActivity
import com.chinky.family.activities.LazyRowDemoActivity
import com.chinky.family.activities.LinearProgressIndicatorDemoActivity
import com.chinky.family.activities.ListItemDemoActivity
import com.chinky.family.activities.ModifierDemoActivity
import com.chinky.family.activities.OutlinedButtonDemoActivity
import com.chinky.family.activities.OutlinedTextFieldDemoActivity
import com.chinky.family.activities.RadioButtonDemoActivity
import com.chinky.family.activities.RowDemoActivity
import com.chinky.family.activities.ScaffoldDemoActivity
import com.chinky.family.activities.SnackbarDemoActivity
import com.chinky.family.activities.SwitchDemoActivity
import com.chinky.family.activities.TextButtonDemoActivity
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
                    Text("Text")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, TextFieldDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("TextField")
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
                    Text("Alert Dialog")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, BoxDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Box")
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
                    Text("Button")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, CardDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Card")
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
                    Text("Checkbox ")
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
                    Text("Circular Progress")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, ColumnDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Column")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent = Intent(
                            this@MainActivity,
                            IconButtonDemoActivity::class.java
                        )
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Icon Button")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, IconDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Icon")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, ImageDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Image")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, LazyColumnDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Lazy Column")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent = Intent(
                            this@MainActivity, LazyRowDemoActivity::class.java
                        )
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Lazy Row")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, ModifierDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Modifier")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, OutlinedButtonDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Outlined Button")
                }
            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, OutlinedTextFieldDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Outlined Text Field ")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, RadioButtonDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Radio Button")
                }

            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, RowDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Row")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, ScaffoldDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Scaffold")
                }

            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, SnackbarDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Snackbar")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, SwitchDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Switch")
                }

            }
            Row {
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, TextButtonDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Text Button")
                }
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@MainActivity, DropDownMenuDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Drop Down Menu")
                }
            }
        }
    }
}