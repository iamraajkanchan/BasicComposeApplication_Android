package com.chinky.family.activities.Composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chinky.family.R
import com.chinky.family.ui.theme.ApplicationColor
import com.chinky.family.ui.theme.ApplicationTheme

class BoxDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayVariousBoxOptions(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayVariousBoxOptions(padding: PaddingValues) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CenteredBox()
            Spacer(modifier = Modifier.height(20.dp))
            RoundedBox()
            Spacer(modifier = Modifier.height(20.dp))
            CircularBox()
            Spacer(modifier = Modifier.height(20.dp))
            ProfileBox()
            Spacer(modifier = Modifier.height(20.dp))
            ChatBubble( "Hello Ivy", "9:10 AM",true)
            Spacer(modifier = Modifier.height(20.dp))
            ChatBubble( "I still hate you!", "6:30 PM",false)
        }
    }

    @Composable
    private fun CenteredBox() {
        Box(
            modifier = Modifier
                .size(125.dp)
                .background(ApplicationColor.Pink80),
            contentAlignment = Alignment.Center
        ) {
            Text("Centered Box", color = ApplicationColor.White, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    private fun RoundedBox() {
        Box(
            modifier = Modifier
                .size(125.dp)
                .shadow(10.dp, shape = RoundedCornerShape(20.dp), true)
                .background(ApplicationColor.Pink80, shape = RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Rounded Box", color = ApplicationColor.White, fontWeight = FontWeight.Light)
        }
    }

    @Composable
    private fun CircularBox() {
        Box(
            modifier = Modifier
                .size(124.dp)
                .background(ApplicationColor.Pink80, shape = RoundedCornerShape(62.dp))
                .clip(RoundedCornerShape(62.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Circular Box", color = ApplicationColor.White, fontWeight = FontWeight.Thin)
        }
    }

    @Composable
    private fun ProfileBox() {
        Box(
            modifier = Modifier
                .size(124.dp)
                .background(ApplicationColor.Transparent, shape = RoundedCornerShape(62.dp))
                .clip(RoundedCornerShape(62.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(62.dp)),
                painter = painterResource(R.drawable.cat_profile),
                contentDescription = "Cat Profile",
                contentScale = ContentScale.FillHeight
            )
            Text("", color = ApplicationColor.White, fontWeight = FontWeight.Thin)
        }
    }

    @Composable
    private fun ChatBubble(
        message: String,
        timestamp: String,
        isFromMe: Boolean
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Card(
                modifier = Modifier
                    .align(if (isFromMe) Alignment.CenterEnd else Alignment.CenterStart)
                    .widthIn(max = 280.dp), // ✅ Fixed: added 'max ='
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (isFromMe) 16.dp else 4.dp,
                    bottomEnd = if (isFromMe) 4.dp else 16.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (isFromMe)
                        ApplicationColor.Orange
                    else
                        ApplicationColor.Green
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp) // ✅ Removed unnecessary fillMaxWidth()
                ) {
                    Text(
                        text = message,
                        color = if (isFromMe)
                            ApplicationColor.White
                        else
                            ApplicationColor.Black
                    )

                    Text(
                        text = timestamp,
                        color = if (isFromMe)
                            ApplicationColor.White.copy(alpha = 0.7f)
                        else
                            ApplicationColor.Black.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.bodySmall, // ✅ Smaller text for timestamp
                        modifier = Modifier
                            .align(Alignment.End) // ✅ Much simpler alignment
                            .padding(top = 4.dp)
                    )
                }
            }

            // ✅ Optional: Add message status indicator
            if (isFromMe) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Sent",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-4).dp, y = (-4).dp)
                        .size(16.dp),
                    tint = ApplicationColor.Orange
                )
            }
        }
    }


}