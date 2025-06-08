package com.chinky.family.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationTheme


/**
Note: This Activity is extending AppCompatActivity. I have not changed it to ComponentActivity so that I could learn the
difference between ComponentActivity and AppCompatActivity.
For a composable activity do not forget to extend ComponentActivity.
If you extend the activity with AppCompatActivity then you have to define the theme in AndroidManifest.xml file
 */

class TextFieldDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    displayVariousTextFieldOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun displayVariousTextFieldOptions(padding: PaddingValues) {
        val context = LocalContext.current
        // When you are using mutableStateOf method for a variable use by remember method.
        var emailAddressText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }

        // Properly remember separate FocusRequesters
        // When you are not using mutableStateOf method for a variable use = remember method.
        val emailFocusRequester = remember { FocusRequester() }
        val passwordFocusRequester = remember { FocusRequester() }

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = emailAddressText,
                onValueChange = { newText ->
                    emailAddressText = newText
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                ),
                label = { Text("Enter email address") },
                modifier = Modifier
                    .focusRequester(emailFocusRequester) // Attach focus requester
                    .focusable(true)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = passwordText,
                onValueChange = { newText ->
                    passwordText = newText
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                label = { Text("Enter password") },
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (emailAddressText.isEmpty()) {
                    Toast.makeText(context, "Email can't be empty!", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }
                if (passwordText.isEmpty()) {
                    Toast.makeText(context, "Password can't be empty!", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (!emailAddressText.isEmpty() && !passwordText.isEmpty()) {
                    submitEmailAndPassword(context)
                }
            }) {
                Text("Submit")
            }
        }

        // Request focus when composable launches
        LaunchedEffect(Unit) {
            emailFocusRequester.requestFocus() // Focus on email field initially
        }
    }

    private fun submitEmailAndPassword(context: Context) {
        Toast.makeText(context, "Email and Password is submitted!", Toast.LENGTH_LONG).show()
    }
}
