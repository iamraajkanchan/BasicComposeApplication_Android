package com.chinky.family.presentation.composableFunctions.activities

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class OutlinedTextFieldDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousTextField(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousTextField(padding: PaddingValues) {
        val spacing = 20.dp
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicOutlinedTextField()
            Spacer(modifier = Modifier.height(spacing))
            ModifiedOutlinedTextField()
        }
    }

    @Composable
    private fun BasicOutlinedTextField() {
        val text = rememberSaveable { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        OutlinedTextField(
            value = text.value,
            onValueChange = { text.value = it },
            label = { Text("Write Something...") },
            modifier = Modifier.focusRequester(focusRequester)
        )
    }

    @Composable
    private fun ModifiedOutlinedTextField() {
        val nameText = rememberSaveable { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        OutlinedTextField(
            value = nameText.value,
            onValueChange = { nameText.value = it },
            label = { Text("Your Name.") },
            modifier = Modifier.focusRequester(focusRequester),
            colors = TextFieldDefaults.colors(
                focusedTextColor = ApplicationColor.Green,
                unfocusedTextColor = ApplicationColor.Orange,
                focusedContainerColor = ApplicationColor.Orange,
                unfocusedContainerColor = ApplicationColor.Green
            )

        )
    }
}