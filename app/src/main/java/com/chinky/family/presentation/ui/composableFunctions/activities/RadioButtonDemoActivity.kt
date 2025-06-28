package com.chinky.family.presentation.ui.composableFunctions.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import com.chinky.family.domain.utils.Utility

class RadioButtonDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousRadioButtonOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousRadioButtonOptions(padding: PaddingValues) {
        val spacing = 20.dp
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing))
            BasicRadioButton()
            Spacer(modifier = Modifier.height(spacing))
            ModifiedRadioButton()
        }
    }

    @Composable
    private fun BasicRadioButton() {
        val isChecked = rememberSaveable { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                onClick = { isChecked.value = !isChecked.value },
                selected = isChecked.value
            )
            Text(modifier = Modifier.padding(10.dp), text = "Basic Radio Button")
        }
    }

    @Composable
    private fun ModifiedRadioButton() {
        val interactionSource = remember { MutableInteractionSource() }
        val isChecked = rememberSaveable { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    (when (it) {
                        is FocusInteraction.Focus -> {
                            Utility.printLog(
                                RadioButtonDemoActivity::class.java,
                                Thread.currentThread().stackTrace[2],
                                "is RadioButton got Focus!"
                            )
                        }

                        is FocusInteraction.Unfocus -> {
                            Utility.printLog(
                                RadioButtonDemoActivity::class.java,
                                Thread.currentThread().stackTrace[2],
                                "is RadioButton lost Focus!"
                            )
                        }

                        is PressInteraction.Press -> {
                            Utility.printLog(
                                RadioButtonDemoActivity::class.java,
                                Thread.currentThread().stackTrace[2],
                                "is RadioButton is pressed!"
                            )
                        }

                        is PressInteraction.Release -> {
                            Utility.printLog(
                                RadioButtonDemoActivity::class.java,
                                Thread.currentThread().stackTrace[2],
                                "is RadiButton is released!"
                            )
                        }

                        is PressInteraction.Cancel -> {
                            Utility.printLog(
                                RadioButtonDemoActivity::class.java,
                                Thread.currentThread().stackTrace[2],
                                "is RadioButton is cancelled!"
                            )
                        }

                        else -> {
                            Utility.printLog(
                                RadioButtonDemoActivity::class.java,
                                Thread.currentThread().stackTrace[2],
                                "is RadioButton got some other interaction!"
                            )
                        }
                    })
                }
            }
            RadioButton(
                onClick = { isChecked.value = !isChecked.value },
                selected = isChecked.value,
                colors = RadioButtonDefaults.colors(
                    selectedColor = ApplicationColor.Green,
                    unselectedColor = ApplicationColor.Orange
                ),
                interactionSource = interactionSource
            )
            Text(text = "Modified Radio Button")
        }
    }


}