package com.chinky.family.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.activities.Composable.AlertDialogDemoActivity
import com.chinky.family.activities.Composable.BoxDemoActivity
import com.chinky.family.activities.Composable.ButtonDemoActivity
import com.chinky.family.activities.Composable.CardDemoActivity
import com.chinky.family.activities.Composable.CheckboxDemoActivity
import com.chinky.family.activities.Composable.CircularProgressIndicatorDemoActivity
import com.chinky.family.activities.Composable.ColumnDemoActivity
import com.chinky.family.activities.Composable.DropDownMenuDemoActivity
import com.chinky.family.activities.Composable.IconButtonDemoActivity
import com.chinky.family.activities.Composable.IconDemoActivity
import com.chinky.family.activities.Composable.ImageDemoActivity
import com.chinky.family.activities.Composable.LazyColumnDemoActivity
import com.chinky.family.activities.Composable.LazyRowDemoActivity
import com.chinky.family.activities.Composable.ModifierDemoActivity
import com.chinky.family.activities.Composable.OutlinedButtonDemoActivity
import com.chinky.family.activities.Composable.OutlinedTextFieldDemoActivity
import com.chinky.family.activities.Composable.RadioButtonDemoActivity
import com.chinky.family.activities.Composable.RowDemoActivity
import com.chinky.family.activities.Composable.ScaffoldDemoActivity
import com.chinky.family.activities.Composable.SnackbarDemoActivity
import com.chinky.family.activities.Composable.SwitchDemoActivity
import com.chinky.family.activities.Composable.TextButtonDemoActivity
import com.chinky.family.activities.Composable.TextDemoActivity
import com.chinky.family.activities.Composable.TextFieldDemoActivity

class ComposableMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                DisplayAllComposeMenu(padding)
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
                            Intent(this@ComposableMenuActivity, TextDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Text")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, TextFieldDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, AlertDialogDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Alert Dialog")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, BoxDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, ButtonDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Button")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, CardDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, CheckboxDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Checkbox ")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent = Intent(
                            this@ComposableMenuActivity,
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
                            Intent(this@ComposableMenuActivity, ColumnDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Column")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent = Intent(
                            this@ComposableMenuActivity,
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
                            Intent(this@ComposableMenuActivity, IconDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Icon")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, ImageDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, LazyColumnDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Lazy Column")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent = Intent(
                            this@ComposableMenuActivity, LazyRowDemoActivity::class.java
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
                            Intent(this@ComposableMenuActivity, ModifierDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Modifier")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(
                                this@ComposableMenuActivity,
                                OutlinedButtonDemoActivity::class.java
                            )
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
                            Intent(
                                this@ComposableMenuActivity,
                                OutlinedTextFieldDemoActivity::class.java
                            )
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Outlined Text Field ")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, RadioButtonDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, RowDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Row")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, ScaffoldDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, SnackbarDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Snackbar")
                }

                Button(
                    modifier = rightButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(this@ComposableMenuActivity, SwitchDemoActivity::class.java)
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
                            Intent(this@ComposableMenuActivity, TextButtonDemoActivity::class.java)
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Text Button")
                }
                Button(
                    modifier = leftButtonModifier,
                    onClick = {
                        val textActivityIntent =
                            Intent(
                                this@ComposableMenuActivity,
                                DropDownMenuDemoActivity::class.java
                            )
                        startActivity(textActivityIntent)
                    }
                ) {
                    Text("Drop Down Menu")
                }
            }
        }
    }
}