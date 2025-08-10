package com.chinky.family.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.chinky.family.domain.utils.printLogcat
import com.chinky.family.presentation.ui.activityLifecycle.ActivityLifecycleInComposeDemo
import com.chinky.family.presentation.ui.cameraXDemo.CameraXDemoActivity
import com.chinky.family.presentation.ui.composableFunctions.activities.ComposableMenuActivity
import com.chinky.family.presentation.ui.locations.LocationInfoDemoActivity
import com.chinky.family.presentation.ui.navigationDemo.NavigationWithObjectArgumentActivity
import com.chinky.family.presentation.ui.navigationDemo.NavigationWithStringArgumentActivity
import com.chinky.family.presentation.ui.networkCall.NetworkCallDemoActivity
import com.chinky.family.presentation.ui.permissionsDemo.PermissionsMenuActivity
import com.chinky.family.presentation.ui.security.SecurityMenuActivity
import com.chinky.family.presentation.ui.services.activities.ServiceMenuActivity
import com.chinky.family.presentation.ui.stateManagements.activities.StateManagementDemoActivity
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayApplicationMenu(padding)
                }
            }
        }
    }

    @Composable
    fun DisplayApplicationMenu(padding: PaddingValues = PaddingValues(16.dp)) {
        val context = LocalContext.current
        val fragmentActivity = context as? FragmentActivity
        var isAuthenticated by remember { mutableStateOf(false) }
        var hasAuthenticated by remember { mutableStateOf(false) }

        // Use a key to ensure this effect runs only once
        LaunchedEffect(key1 = Unit) {
            val biometricManager = BiometricManager.from(context)
            val canAuth = biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.BIOMETRIC_WEAK or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )

            if (canAuth == BiometricManager.BIOMETRIC_SUCCESS && fragmentActivity != null) {
                val executor = ContextCompat.getMainExecutor(context)
                val biometricPrompt = BiometricPrompt(
                    fragmentActivity,
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            isAuthenticated = true
                            hasAuthenticated = true
                            MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "hasAuthenticated : $hasAuthenticated")
                            MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "isAuthenticated : $isAuthenticated")
                        }

                        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                            isAuthenticated = false
                            hasAuthenticated = true
                            MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "hasAuthenticated : $hasAuthenticated")
                            MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "isAuthenticated : $isAuthenticated")
                        }

                        override fun onAuthenticationFailed() {
                            isAuthenticated = false
                            hasAuthenticated = false
                            MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "hasAuthenticated : $hasAuthenticated")
                            MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "isAuthenticated : $isAuthenticated")
                        }
                    }
                )

                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Secure Access")
                    .setSubtitle("Authenticate with face or fingerprint")
                    .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                    .build()

                biometricPrompt.authenticate(promptInfo)
            } else {
                // Biometric not available, handle fallback
                isAuthenticated = true
                hasAuthenticated = true
                MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "hasAuthenticated : $hasAuthenticated")
                MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "isAuthenticated : $isAuthenticated")
            }
        }
        MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "hasAuthenticated : $hasAuthenticated")
        MainActivity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "isAuthenticated : $isAuthenticated")
        if (hasAuthenticated) {
            if (isAuthenticated) {
                DisplayMenuButtons(padding, context)
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sorry, you are not allowed to access this application",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        } else {
            // Show loading or a blank screen while authentication is in progress
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    fun DisplayMenuButtons(padding: PaddingValues, context: Context) {
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    val intent = Intent(context, ComposableMenuActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Composable Functions")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, StateManagementDemoActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("State Management")
            }
            Button(
                onClick = {
                    val intent = Intent(context, ServiceMenuActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Services")
            }
            Button(
                onClick = {
                    val intent = Intent(context, NetworkCallDemoActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Network Call")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, NavigationWithStringArgumentActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Navigation Demo with String")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, NavigationWithObjectArgumentActivity::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Navigation Demo with Object")
            }
            Button(
                onClick = {
                    val intent =
                        Intent(context, ActivityLifecycleInComposeDemo::class.java)
                    startActivity(intent)
                },
            ) {
                Text("Activity Lifecycle in Compose")
            }
            Button(onClick = {
                val intent = Intent(context, LocationInfoDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("Location Into")
            }
            Button(onClick = {
                val intent = Intent(context, SecurityMenuActivity::class.java)
                startActivity(intent)
            }) {
                Text("Security Demo")
            }
            Button(onClick = {
                val intent = Intent(context, PermissionsMenuActivity::class.java)
                startActivity(intent)
            }) {
                Text("Permissions Demo")
            }
            Button(onClick = {
                val intent = Intent(context, CameraXDemoActivity::class.java)
                startActivity(intent)
            }) {
                Text("CameraX Demo")
            }
        }
    }
}