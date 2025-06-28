package com.chinky.family.presentation.ui.composableFunctions.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chinky.family.R
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class ImageDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousImageOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousImageOptions(paddingValues: PaddingValues) {
        val context = LocalContext.current
        val spacing = 20.dp
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(spacing))
            BasicSVGIconImage(context)
            Spacer(Modifier.height(spacing))
            BasicJPGIconImage(context)
            Spacer(Modifier.height(spacing))
            BasicWEBPIconImage(context)
        }
    }

    @Composable
    private fun BasicSVGIconImage(context: Context) {
        Image(
            painter = painterResource(R.drawable.ic_user_svg),
            contentDescription = "User Profile",
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    Toast.makeText(context, "You clicked SVG Image!", Toast.LENGTH_SHORT).show()
                },
        )
    }

    @Composable
    private fun BasicJPGIconImage(context: Context) {
        Image(
            painter = painterResource(R.drawable.landscape_river),
            contentDescription = "Landscape River",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast.makeText(context, "You clicked JPG Image!", Toast.LENGTH_SHORT).show()
                },
            contentScale = ContentScale.Fit
        )
    }

    @Composable
    private fun BasicWEBPIconImage(context: Context) {
        Image(
            painter = painterResource(R.drawable.baby),
            contentDescription = "Baby",
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    Toast.makeText(context, "You clicked Webp Image!", Toast.LENGTH_SHORT).show()
                },
            contentScale = ContentScale.Fit
        )
    }
}