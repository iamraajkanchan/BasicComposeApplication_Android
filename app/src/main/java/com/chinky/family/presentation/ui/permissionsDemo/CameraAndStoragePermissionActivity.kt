package com.chinky.family.presentation.ui.permissionsDemo

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.exifinterface.media.ExifInterface
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class CameraAndStoragePermissionActivity : ComponentActivity() {

    private val requestPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_MEDIA_IMAGES
    )

    private val PERMISSION_GRANTED = android.content.pm.PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold { paddingValues ->
                    CameraAndStoragePermissionScreen(paddingValues)
                }
            }
        }
    }


    @Composable
    fun CameraAndStoragePermissionScreen(paddingValues: PaddingValues) {
        val context = LocalContext.current
        val permissionsGranted = remember { mutableStateOf(false) }
        val showSelector = remember { mutableStateOf(false) }
        val selectedBitmap = remember { mutableStateOf<Bitmap?>(null) }
        val permissionsLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            permissionsGranted.value = result.all { it.value }
        }
        LaunchedEffect(Unit) {
            val notGranted = requestPermissions.any {
                ActivityCompat.checkSelfPermission(context, it) != PERMISSION_GRANTED
            }
            if (notGranted) {
                permissionsLauncher.launch(requestPermissions)
            } else {
                permissionsGranted.value = true
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            selectedBitmap.value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Fit
                )
            }

            Button(onClick = {
                if (permissionsGranted.value) {
                    showSelector.value = true
                } else {
                    Toast.makeText(context, "Please grant permissions", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Choose Image")
            }

            if (showSelector.value) {
                CameraAndGallerySelector(
                    onBitmapSelected = {
                        selectedBitmap.value = it
                        showSelector.value = false
                    }
                )
            }
        }
    }

    @Composable
    fun CameraAndGallerySelector(onBitmapSelected: (Bitmap?) -> Unit) {
        val context = LocalContext.current
        var cameraImageUri by remember { mutableStateOf<Uri?>(null) }
        val cameraLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success && cameraImageUri != null) {
                val bitmap = context.contentResolver.openInputStream(cameraImageUri!!)?.use {
                    val originalBitmap = BitmapFactory.decodeStream(it)
                    correctImageRotation(context, cameraImageUri!!, originalBitmap)
                }
                onBitmapSelected(bitmap)
            }
        }

        val galleryLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->
            val bitmap = uri?.let {uri ->
                val originalBitmap = context.contentResolver.openInputStream(uri)?.use { stream ->
                    BitmapFactory.decodeStream(stream)
                }
                originalBitmap?.let { correctImageRotation(context, uri, originalBitmap) }
            }
            onBitmapSelected(bitmap)
        }

        FileSelectorBottomSheet(
            onCameraClick = {
                val uri = createImageUri(context)
                cameraImageUri = uri
                uri?.let { cameraLauncher.launch(it) }
            },
            onGalleryClick = {
                galleryLauncher.launch("image/*")
            }
        )
    }

    @Composable
    fun FileSelectorBottomSheet(
        onCameraClick: () -> Unit,
        onGalleryClick: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Choose an option", style = MaterialTheme.typography.titleMedium)
            Button(onClick = onCameraClick, modifier = Modifier.fillMaxWidth()) {
                Text("Camera")
            }
            Button(onClick = onGalleryClick, modifier = Modifier.fillMaxWidth()) {
                Text("Gallery")
            }
        }
    }

    fun correctImageRotation(context: Context, uri: Uri, bitmap: Bitmap): Bitmap {
        val exif = context.contentResolver.openInputStream(uri)?.use { ExifInterface(it) }
        val orientation = exif?.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        ) ?: ExifInterface.ORIENTATION_NORMAL

        val rotationDegrees = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }

        return if (rotationDegrees != 0) {
            val matrix = Matrix().apply { postRotate(rotationDegrees.toFloat()) }
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } else {
            bitmap
        }
    }
    fun createImageUri(context: Context): Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        return context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
    }

}