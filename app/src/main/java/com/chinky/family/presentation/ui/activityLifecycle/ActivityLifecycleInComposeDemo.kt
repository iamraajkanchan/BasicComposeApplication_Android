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
import com.chinky.family.domain.utils.printLogcat
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import kotlin.jvm.java

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
                ActivityLifecycleInComposeDemo::class.java.printLogcat("key1 : ${lifecycleState.value}")
            }
            Text(lifecycleState.value, color = ApplicationColor.Orange)
        }
    }

    override fun onStart() {
        super.onStart()
        ActivityLifecycleInComposeDemo::class.java.printLogcat("onStart")
        lifecycleState.value = "onStart"
    }

    override fun onResume() {
        super.onResume()
        lifecycleState.value = "onResume"
    }

    override fun onPause() {
        ActivityLifecycleInComposeDemo::class.java.printLogcat("onPause")
        lifecycleState.value = "onPause"
        super.onPause()
    }

    override fun onStop() {
        ActivityLifecycleInComposeDemo::class.java.printLogcat("onStop")
        lifecycleState.value = "onStop"
        super.onStop()
    }

    override fun onDestroy() {
        ActivityLifecycleInComposeDemo::class.java.printLogcat("onDestroy")
        lifecycleState.value = "onDestroy"
        super.onDestroy()
    }
}