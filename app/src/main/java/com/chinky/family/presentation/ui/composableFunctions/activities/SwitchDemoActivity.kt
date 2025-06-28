package com.chinky.family.presentation.ui.composableFunctions.activities

import android.os.Bundle
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class SwitchDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousSwitchOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousSwitchOptions(padding: PaddingValues) {
        val spacing = 20.dp
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing))
            BasicSwitch()
            Spacer(modifier = Modifier.height(spacing))
            ModifiedSwitch()
        }
    }

    @Composable
    private fun BasicSwitch() {
        val switchState = remember { mutableStateOf(false) }
        Switch(
            checked = switchState.value,
            onCheckedChange = {
                switchState.value = it
            }
        )
        if (switchState.value) {
            Text("Basic Switch is ON")
        } else {
            Text("Basic Switch is OFF")
        }
    }

    @Composable
    private fun ModifiedSwitch() {
        val switchState = remember { mutableStateOf(false) }
        Switch(
            checked = switchState.value,
            onCheckedChange = {
                switchState.value = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = ApplicationColor.Green,
                checkedTrackColor = ApplicationColor.Orange,
                checkedBorderColor = ApplicationColor.Orange,
                uncheckedThumbColor = ApplicationColor.Orange,
                uncheckedTrackColor = ApplicationColor.Green,
                uncheckedBorderColor = ApplicationColor.Green
            )
        )
        if (switchState.value) {
            Text("Modified Switch is ON")
        } else {
            Text("Modified Switch is OFF")
        }
    }
}