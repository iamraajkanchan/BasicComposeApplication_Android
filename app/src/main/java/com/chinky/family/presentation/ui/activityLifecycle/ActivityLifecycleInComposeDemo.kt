package com.chinky.family.presentation.ui.activityLifecycle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chinky.family.domain.utils.Utility
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class ActivityLifecycleInComposeDemo : ComponentActivity() {

    private val lifecycleState = mutableStateOf("onCreate")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleState.value = "onCreate"
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold { paddingValues ->
                    DisplayLifecycleState(paddingValues)
                }
            }
        }
    }

    @Composable
    private fun DisplayLifecycleState(paddingValues: PaddingValues) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LaunchedEffect(key1 = lifecycleState) {
                Utility.printLog(
                    ActivityLifecycleInComposeDemo::class.java,
                    Thread.currentThread().stackTrace[2],
                    "key1 : ${lifecycleState.value}"
                )
            }
            Text(lifecycleState.value, color = ApplicationColor.Orange)
        }
    }

    override fun onStart() {
        super.onStart()
        Utility.printLog(
            ActivityLifecycleInComposeDemo::class.java,
            Thread.currentThread().stackTrace[2],
            "onStart"
        )
        lifecycleState.value = "onStart"
    }

    override fun onResume() {
        super.onResume()
        lifecycleState.value = "onResume"
    }

    override fun onPause() {
        Utility.printLog(
            ActivityLifecycleInComposeDemo::class.java,
            Thread.currentThread().stackTrace[2],
            "onPause"
        )
        lifecycleState.value = "onPause"
        super.onPause()
    }

    override fun onStop() {
        Utility.printLog(
            ActivityLifecycleInComposeDemo::class.java,
            Thread.currentThread().stackTrace[2],
            "onStop"
        )
        lifecycleState.value = "onStop"
        super.onStop()
    }

    override fun onDestroy() {
        Utility.printLog(
            ActivityLifecycleInComposeDemo::class.java,
            Thread.currentThread().stackTrace[2],
            "onDestroy"
        )
        lifecycleState.value = "onDestroy"
        super.onDestroy()
    }
}