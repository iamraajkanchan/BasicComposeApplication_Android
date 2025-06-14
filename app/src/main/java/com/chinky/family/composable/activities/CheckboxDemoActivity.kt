package com.chinky.family.composable.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class CheckboxDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousCheckboxOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousCheckboxOptions(padding: PaddingValues) {
        var isFirstCheckboxChecked by remember { mutableStateOf(false) }
        var isSecondCheckboxChecked by remember { mutableStateOf(false) }
        var isThirdCheckboxChecked by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { isFirstCheckboxChecked = !isFirstCheckboxChecked }
            ) {
                Checkbox(
                    checked = isFirstCheckboxChecked,
                    onCheckedChange = { isFirstCheckboxChecked = it }
                )
                Spacer(modifier = Modifier.width(8.dp)) // horizontal space between checkbox and label
                Text("First Normal Checkbox")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { isSecondCheckboxChecked = !isSecondCheckboxChecked }
            ) {
                Checkbox(
                    checked = isSecondCheckboxChecked,
                    onCheckedChange = { isSecondCheckboxChecked = it }
                )
                Spacer(modifier = Modifier.width(8.dp)) // horizontal space between checkbox and label
                Text("Second Normal Checkbox")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { isThirdCheckboxChecked = !isThirdCheckboxChecked }
            ) {
                Checkbox(
                    checked = isThirdCheckboxChecked,
                    onCheckedChange = { isThirdCheckboxChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = ApplicationColor.Orange,
                        uncheckedColor = ApplicationColor.Green,
                        checkmarkColor = ApplicationColor.White,
                    )
                )
                Spacer(modifier = Modifier.width(8.dp)) // horizontal space between checkbox and label
                Text("Third Modified UI Checkbox")
            }
        }
    }
}