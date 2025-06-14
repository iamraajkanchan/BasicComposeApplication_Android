package com.chinky.family.composable.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class CardDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousCardOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousCardOptions(padding: PaddingValues) {
        val spacingHeight = 10.dp
        val blackAndWhiteGradient = Brush.linearGradient(
            colors = listOf(ApplicationColor.Black, ApplicationColor.White),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f),
            tileMode = TileMode.Repeated
        )
        // Sunset Gradient - Warm orange to pink
        val sunsetGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFFF7E5F), Color(0xFFFEB47B)),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )

        // Ocean Gradient - Deep blue to turquoise
        val oceanGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF1E3C72), Color(0xFF2A5298)),
            start = Offset(0f, 0f),
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )

        // Forest Gradient - Dark green to light green
        val forestGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF134E5E), Color(0xFF71B280)),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )

        // Purple Dream - Deep purple to lavender
        val purpleDreamGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF667EEA), Color(0xFF764BA2)),
            start = Offset(0f, Float.POSITIVE_INFINITY),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )

        // Autumn Gradient - Orange to red
        val autumnGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFFF6B6B), Color(0xFFFFE66D)),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )

        // Mint Fresh - Light green to mint
        val mintFreshGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF00D2FF), Color(0xFF3A7BD5)),
            start = Offset(0f, 0f),
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )

        // Rose Gold - Pink to gold
        val roseGoldGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFF093FB), Color(0xFFF5576C)),
            start = Offset(Float.POSITIVE_INFINITY, 0f),
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )

        // Night Sky - Dark blue to black
        val nightSkyGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF0F2027), Color(0xFF203A43), Color(0xFF2C5364)),
            start = Offset(0f, 0f),
            end = Offset(0f, Float.POSITIVE_INFINITY)
        )

        // Warm Sunset - Three color gradient
        val warmSunsetGradient = Brush.linearGradient(
            colors = listOf(Color(0xFFFF9A9E), Color(0xFFFECAA6), Color(0xFFFFC3A0)),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )

        // Cool Blues - Light to dark blue
        val coolBluesGradient = Brush.linearGradient(
            colors = listOf(Color(0xFF74B9FF), Color(0xFF0984E3)),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, 0f)
        )

        // Radial Gradient Examples
        val radialSunsetGradient = Brush.radialGradient(
            colors = listOf(Color(0xFFFF7E5F), Color(0xFFFEB47B)),
            radius = 500f
        )

        val radialOceanGradient = Brush.radialGradient(
            colors = listOf(Color(0xFF1E3C72), Color(0xFF2A5298)),
            radius = 300f
        )

        // Vertical Gradients for backgrounds
        val verticalSkyGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF87CEEB), Color(0xFFE0F6FF))
        )

        val verticalDarkGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF2C3E50), Color(0xFF34495E))
        )

        // Horizontal Gradients for UI elements
        val horizontalWarmGradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFFFF6B6B), Color(0xFFFFE66D))
        )

        val horizontalCoolGradient = Brush.horizontalGradient(
            colors = listOf(Color(0xFF4ECDC4), Color(0xFF44A08D))
        )
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(blackAndWhiteGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(sunsetGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(oceanGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(forestGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(purpleDreamGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(autumnGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(mintFreshGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(roseGoldGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(nightSkyGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(warmSunsetGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(coolBluesGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(radialSunsetGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(radialOceanGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(verticalSkyGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(verticalDarkGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(horizontalWarmGradient)
            Spacer(modifier = Modifier.height(spacingHeight))
            BaseGradientCard(horizontalCoolGradient)
        }
    }

    @Composable
    private fun BaseGradientCard(brush: Brush) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                Modifier
                    .background(brush = brush)
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Text("Hello from Jetpack Card!", Modifier.padding(16.dp))
            }
        }
    }
}