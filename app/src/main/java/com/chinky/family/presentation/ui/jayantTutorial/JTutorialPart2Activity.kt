package com.chinky.family.presentation.ui.jayantTutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.chinky.family.presentation.ui.theme.ApplicationTheme

/**
 * In this tutorial we are going to learn, how to define a Customized Top Bar,
 * with actions, navigationIcons and floatingActionButton.
 * */
class JTutorialPart2Activity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text("Part 2 Tutorial") },
                        navigationIcon = {
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                            }
                        },
                        actions = {
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications")
                            }
                            IconButton(onClick = {}) {
                                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton (
                        onClick = {},
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                    }
                },
                floatingActionButtonPosition = androidx.compose.material3.FabPosition.End,
                    contentWindowInsets = WindowInsets.Companion.displayCutout
                )
                {paddingValues ->

                }
            }
        }
    }
}