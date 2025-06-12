package com.chinky.family.activities.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class ColumnDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousColumnOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousColumnOptions(paddingValues: PaddingValues) {
        CreateNestedColumnWithFixedWidthAndHeight(paddingValues)
    }

    @Composable
    private fun CreateNestedColumnWithFixedWidthAndHeight(paddingValues: PaddingValues) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            CreateColumnWithFixedHeightAndMaximumWidth(paddingValues)
            CreateColumnWithFixedWidthAndMaximumHeight(paddingValues)
        }
    }

    @Composable
    private fun CreateColumnWithFixedHeightAndMaximumWidth(paddingValues: PaddingValues) {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .height(100.dp)
                .background(color = ApplicationColor.Pink40)
        ) { }
    }

    @Composable
    private fun CreateColumnWithFixedWidthAndMaximumHeight(paddingValues: PaddingValues) {
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxHeight()
                .width(100.dp)
                .background(color = ApplicationColor.Purple40)
        ) { }
    }
}