package com.chinky.family.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                    displayVariousTextFieldOptions()
                }
            }
        }
    }

    @Composable
    private fun displayVariousTextFieldOptions() {

    }
}