package com.chinky.family.activities.Composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class CircularProgressIndicatorDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousCircularProgressIndicatorOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousCircularProgressIndicatorOptions(padding: PaddingValues) {
        val progress = remember { mutableFloatStateOf(.64f) }
        val spacing = 10.dp
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            // verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(spacing))
            DefaultCircularProgressBar()
            Spacer(modifier = Modifier.height(spacing))
            ModifiedCircularProgressBarIndicator()
            Spacer(modifier = Modifier.height(spacing))
            // set the value from 0 to 1
            IndeterminateCircularProgressBarIndicator(progress.floatValue)
        }
    }

    @Composable
    private fun DefaultCircularProgressBar() {
        CircularProgressIndicator()
    }

    @Composable
    private fun ModifiedCircularProgressBarIndicator() {
        CircularProgressIndicator(
            color = ApplicationColor.Green,
            strokeWidth = 2.dp,
        )
    }

    @Composable
    private fun IndeterminateCircularProgressBarIndicator(progress: Float) {
        CircularProgressIndicator(progress = { progress })
    }
}