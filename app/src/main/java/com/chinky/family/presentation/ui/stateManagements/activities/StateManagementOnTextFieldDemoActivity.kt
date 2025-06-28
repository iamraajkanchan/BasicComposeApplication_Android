package com.chinky.family.presentation.ui.stateManagements.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class StateManagementOnTextFieldDemoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                Column {
                    ExampleOfStateManagementForTextFieldWithRemember(padding)
                    Spacer(modifier = Modifier.height(50.dp))
                    ExampleOfStateManagementForTextFieldWithRememberSave(padding)
                }
            }
        }
    }

    @Composable
    private fun ExampleOfStateManagementForTextFieldWithRemember(paddingValues: PaddingValues) {
        val text = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Using remember State")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = text.value, onValueChange = { text.value = it })
            Text(text = "Hello ${text.value}")
        }
    }

    @Composable
    private fun ExampleOfStateManagementForTextFieldWithRememberSave(paddingValues: PaddingValues) {
        val text = rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text("Using rememberSaveable State")
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = text.value, onValueChange = { text.value = it })
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Hello ${text.value}")
        }
    }
}