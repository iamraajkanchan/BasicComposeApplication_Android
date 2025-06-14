package com.chinky.family.composable.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.chinky.family.R
import com.chinky.family.ui.theme.ApplicationTheme

class IconButtonDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousIconButtonOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousIconButtonOptions(paddingValues: PaddingValues) {
        val context = LocalContext.current
        val spacing = 10.dp
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(spacing))
            BasicIconButton(context)
            Spacer(modifier = Modifier.height(spacing))
            BasicFilledIconButton(context)
            Spacer(modifier = Modifier.height(spacing))
            BasicFilledTonalIconButton(context)
            Spacer(modifier = Modifier.height(spacing))
            BasicOutlinedIconButton(context)
            Spacer(modifier = Modifier.height(spacing))
            BasicIconToggleButton(context)
        }
    }

    @Composable
    private fun BasicIconButton(context: Context) {
        IconButton(onClick = {
            Toast.makeText(
                context,
                "User Profile Clicked From Basic Icon Button",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(painter = painterResource(R.drawable.ic_user), "User Profile")
        }
    }

    @Composable
    private fun BasicFilledIconButton(context: Context) {
        FilledIconButton(onClick = {
            Toast.makeText(
                context,
                "User Profile Clicked From Filled Icon Button",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(painter = painterResource(R.drawable.ic_user), "User Profile")
        }
    }

    @Composable
    private fun BasicFilledTonalIconButton(context: Context) {
        FilledTonalIconButton(onClick = {
            Toast.makeText(
                context,
                "User Profile Clicked From Filled Tonal Icon Button",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(painter = painterResource(R.drawable.ic_user), "User Profile")
        }
    }

    @Composable
    private fun BasicOutlinedIconButton(context: Context) {
        OutlinedIconButton(onClick = {
            Toast.makeText(
                context,
                "User Profile Clicked From Outlined Icon Button",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(painter = painterResource(R.drawable.ic_user), "User Profile")
        }
    }

    @Composable
    private fun BasicIconToggleButton(context: Context) {
        IconToggleButton(checked = true, onCheckedChange = {
            Toast.makeText(
                context,
                "User Profile Clicked From Icon Toggle Button",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Icon(painter = painterResource(R.drawable.ic_user), "User Profile")
        }
    }
}