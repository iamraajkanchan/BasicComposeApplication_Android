package com.chinky.family.activities.composable

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.chinky.family.R
import com.chinky.family.ui.theme.ApplicationTheme
import com.chinky.family.utils.Utility


/**
Note: This Activity is extending AppCompatActivity. I have not changed it to ComponentActivity so that I could learn the
difference between ComponentActivity and AppCompatActivity.
For a composable activity do not forget to extend ComponentActivity.
If you extend the activity with AppCompatActivity then you have to define the theme in AndroidManifest.xml file
 */

class TextFieldDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousTextFieldOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousTextFieldOptions(padding: PaddingValues) {
        val context = LocalContext.current
        // When you are using mutableStateOf method for a variable use by remember method.
        // If you are using by then the variable will not get the value property.
        var emailAddressText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(true) }
        // When you are not using mutableStateOf method for a variable use = remember method.
        // If you are using = then the variable will get the value property.
        val emailFocusRequester = remember { FocusRequester() }
        val passwordFocusRequester = remember { FocusRequester() }
        val submitFocusRequester = remember { FocusRequester() }
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
                    Utility.printLog(
                        TextFieldDemoActivity::class.java,
                        Thread.currentThread().stackTrace[2],
                        "length of newText in emailTextField: ${newText.length}"
                    )
                    emailAddressText = newText
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    passwordFocusRequester.requestFocus()
                }),
                label = { Text("Enter email address") },
                modifier = Modifier.focusRequester(emailFocusRequester)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = passwordText,
                onValueChange = { newText ->
                    Utility.printLog(
                        TextFieldDemoActivity::class.java,
                        Thread.currentThread().stackTrace[2],
                        "length of newText in passwordTextField: ${newText.length}"
                    )
                    passwordText = newText
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    passwordFocusRequester.freeFocus()
                    submitFocusRequester.requestFocus()
                }),
                label = { Text("Enter password") },
                visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                        Icon(
                            // imageVector = if (isPasswordVisible) Icons.Filled.ArrowDropDown else Icons.Filled.KeyboardArrowDown,
                            painter = painterResource(id = if (isPasswordVisible) R.drawable.ic_hidden_password else R.drawable.ic_visible_password),
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                },
                modifier = Modifier
                    .focusRequester(passwordFocusRequester)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.focusRequester(submitFocusRequester),
                onClick = {
                    if (emailAddressText.isEmpty()) {
                        Toast.makeText(context, "Email can't be empty!", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }
                    if (passwordText.isEmpty()) {
                        Toast.makeText(context, "Password can't be empty!", Toast.LENGTH_SHORT)
                            .show()
                        return@Button
                    }
                    if (!emailAddressText.isEmpty() && !passwordText.isEmpty()) {
                        submitFocusRequester.requestFocus()
                        submitEmailAndPassword(context)
                    }
                }) {
                Text("Submit")
            }
        }
        // Just like LaunchedEffect function you also have SideEffect function.
        // LaunchedEffect runs only once per key changes. LaunchedEffect support coroutines.
        // SideEffect runs everytime the UI recomposes. SideEffect does not support coroutines.
        LaunchedEffect(Unit) {
            emailFocusRequester.requestFocus() // Focus on email field initially
        }
    }

    private fun submitEmailAndPassword(context: Context) {
        Toast.makeText(context, "Email and Password is submitted!", Toast.LENGTH_LONG).show()
    }
}
