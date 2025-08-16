package com.chinky.family.presentation.ui.jayantTutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chinky.family.presentation.ui.common.AppTopBar
import com.chinky.family.presentation.ui.theme.ApplicationTheme

/**
 * In this tutorial we are going to learn, how to define a Top
 * */
class JTutorialPart3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(topBar = {
                    AppTopBar("Part 3 Tutorial", this)
                })
                {paddingValues ->
                    ShowSwitch(paddingValues)
                }
            }
        }
    }

    @Composable
    fun ShowSwitch(paddingValues: PaddingValues) {
        var isChecked by remember { mutableStateOf(true) }
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
        }
    }
}