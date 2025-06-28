package com.chinky.family.presentation.ui.composableFunctions.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class LazyRowDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousLazyColumnOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousLazyColumnOptions(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                val intent = Intent(this@LazyRowDemoActivity, LazyRowBasicDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Basic Lazy Row")
            }
            Button(onClick = {
                val intent = Intent(this@LazyRowDemoActivity, LazyRowWithSelectionDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Lazy Row With Selection")
            }
            Button(onClick = {
                val intent = Intent(this@LazyRowDemoActivity, LazyRowWithSearchDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Lazy Row With Search")
            }
        }
    }
}