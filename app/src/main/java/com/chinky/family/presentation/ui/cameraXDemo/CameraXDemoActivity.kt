package com.chinky.family.presentation.ui.cameraXDemo

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Surface.ROTATION_0
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.chinky.family.R
import com.chinky.family.domain.utils.printLogcat
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraXDemoActivity : ComponentActivity() {

    private val requestPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES
    )

    private val PERMISSION_GRANTED = android.content.pm.PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold {paddingValues ->
                    CaptureImageSection(paddingValues)
                }
            }
        }
        CameraXDemoActivity::class.java.printLogcat(null)
    }

    @Composable
    private fun CaptureImageSection(padding: PaddingValues) {
        val context = LocalContext.current;
        val permissionsGranted = remember { mutableStateOf(false) }
        val cameraPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            permissionsGranted.value = result.all { it.value }
        }
        val lifecycleOwner = LocalLifecycleOwner.current
        val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
        val outputFileUri = remember { mutableStateOf<Uri?>(null) }
        val imageCapture = remember {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .setTargetRotation(context.display.rotation)
                    .build()
            } else {
                ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                    .setTargetRotation(ROTATION_0)
                    .build()
            }
        }
        LaunchedEffect(Unit) {
            val notGranted = requestPermissions.any {
                ActivityCompat.checkSelfPermission(context, it) != PERMISSION_GRANTED
            }
            if (notGranted) {
                cameraPermissionLauncher.launch(requestPermissions)
            } else {
                permissionsGranted.value = true
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { ctx ->
                    // Create a PreviewView to display the camera feed
                    val previewView = PreviewView(ctx).apply {
                        this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    }
                    // Get CameraProvider instance
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                        // Set up the Preview Use Case
                        val preview = Preview.Builder()
                            .build()
                            .also {
                                it.setSurfaceProvider(previewView.surfaceProvider)
                            }

                        // Select back camera as a default
                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()

                        try {
                            // Unbind any previously bound use cases
                            cameraProvider.unbindAll()

                            // Bind use cases to camera
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageCapture // Bind ImageCapture use case
                            )

                        } catch (exc: Exception) {
                            CameraXDemoActivity::class.java.printLogcat("CameraXApp Use case binding failed $exc")
                            Toast.makeText(ctx, "Camera setup failed: ${exc.message}", Toast.LENGTH_LONG).show()
                        }
                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Box(
                modifier = Modifier.size(60.dp), // Use a fixed size for the button area
                contentAlignment = Alignment.Center,
            ) {
                // The outline circle should be a background or the main clickable area
                // We use the outline as the primary button image
                Image(
                    painter = painterResource(id = R.drawable.ic_outline_circle),
                    contentDescription = "Capture Image Button Outline",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            if (permissionsGranted.value) {
                                takePhoto(context, cameraExecutor, imageCapture) { uri ->
                                    outputFileUri.value = uri

                                    runOnUiThread {
                                        Toast.makeText(context, "Photo captured: $uri", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                cameraPermissionLauncher.launch(requestPermissions)
                            }
                        }
                )
                // The filled circle is placed inside the outline as the inner part of the button
                Image(
                    painter = painterResource(id = R.drawable.ic_filled_circle),
                    contentDescription = "Capture Image Button Filled",
                    modifier = Modifier.size(40.dp) // Make this slightly smaller to fit inside the outline
                )
            }
        }
    }

    private fun takePhoto(
        context: Context,
        cameraExecutor: ExecutorService,
        imageCapture: ImageCapture,
        onPhotoSaved: (Uri) -> Unit
    ) {
        // Create output file to hold the image
        val photoFile = File(
            getOutputDirectory(context),
            SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS",
                Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener which is triggered after photo has been taken
        imageCapture.takePicture(
            outputOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    CameraXDemoActivity::class.java.printLogcat("CameraXApp :: Photo capture failed: ${exc.message}")
                    Toast.makeText(context, "Photo capture failed: ${exc.message}", Toast.LENGTH_SHORT).show()
                }
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    onPhotoSaved(savedUri)
                }
            }
        )
    }

    // Helper function to get a directory for saving photos
    private fun getOutputDirectory(context: Context): File {
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else context.filesDir
    }

}