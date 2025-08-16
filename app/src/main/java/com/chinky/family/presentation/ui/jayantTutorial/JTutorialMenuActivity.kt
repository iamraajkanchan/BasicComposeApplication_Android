package com.chinky.family.presentation.ui.jayantTutorial

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chinky.family.presentation.ui.common.AppActivityButton
import com.chinky.family.presentation.ui.common.AppTopBar
import com.chinky.family.presentation.ui.theme.ApplicationTheme

/**
 * Jayant YouTube Tutorial
 * Url: https://www.youtube.com/watch?v=tl8RLM6Sy9s&list=PL4EnMCc01RC0B9kmeZq7xUGpd0wlQkd40
 * */
class JTutorialMenuActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(topBar = { AppTopBar("Jayant Tutorial", this) })
                {
                    DisplayJTutorialMenu(it)
                }
            }
        }
    }

    @Composable
    fun DisplayJTutorialMenu(padding: PaddingValues) {
        Column(modifier = Modifier.padding(padding).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            AppActivityButton(this@JTutorialMenuActivity, JTutorialPart2Activity::class.java) {
                Text("Part 2")
            }
            AppActivityButton(this@JTutorialMenuActivity, JTutorialPart3Activity::class.java) {
                Text("Part 3")
            }
            AppActivityButton(this@JTutorialMenuActivity, JTutorialPart4Activity::class.java) {
                Text("Part 4")
            }
            AppActivityButton(this@JTutorialMenuActivity, JTutorialPart5Activity::class.java) {
                Text("Part 5")
            }
        }
    }
}