package com.chinky.family.activities.Composable

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.chinky.family.R
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class IconDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousIconOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousIconOptions(padding: PaddingValues) {
        val spacing = 10.dp
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicIcon()
            Spacer(Modifier.height(spacing))
            ModifiedIcon()
            Spacer(Modifier.height(spacing))
            IconWithClick(context)
            Spacer(Modifier.height(spacing))
            BasicDrawableIcon(context)
            Spacer(Modifier.height(spacing))
            ModifiedDrawableIcon(context)
        }
    }

    @Composable
    private fun BasicIcon() {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_user),
            contentDescription = "User Profile"
        )
    }

    @Composable
    private fun ModifiedIcon() {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_user),
            contentDescription = "User Profile",
            tint = ApplicationColor.Orange
        )
    }

    @Composable
    private fun IconWithClick(context: Context) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_user),
            contentDescription = "User Profile",
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "You clicked icon with click!", Toast.LENGTH_SHORT)
                        .show()
                }
                .size(50.dp)
        )
    }

    @Composable
    private fun BasicDrawableIcon(context: Context) {
        Icon(
            painter = painterResource(R.drawable.ic_user_svg),
            contentDescription = "User Profile",
            modifier = Modifier
                .clickable {
                    Toast.makeText(context, "You clicked icon with click!", Toast.LENGTH_SHORT)
                        .show()
                }
                .size(50.dp)
        )
    }

    @Composable
    private fun ModifiedDrawableIcon(context: Context) {
        Icon(
            painter = painterResource(R.drawable.ic_user_svg),
            contentDescription = "User Profile",
            modifier = Modifier
                .clickable(
                    indication = rememberRipple(color = ApplicationColor.Orange), // Change ripple color here
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Toast.makeText(context, "You clicked icon with click!", Toast.LENGTH_SHORT)
                        .show()
                }
                .size(50.dp)
        )
    }

}