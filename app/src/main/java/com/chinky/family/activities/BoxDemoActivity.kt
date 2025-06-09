package com.chinky.family.activities

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
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
            ChatBubble(true)
            Spacer(modifier = Modifier.height(20.dp))
            ChatBubble(false)
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
    private fun ChatBubble(isFromMe: Boolean) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
        {
            Card(
                modifier = Modifier
                    .align(if (isFromMe) Alignment.CenterEnd else Alignment.CenterStart)
                    .widthIn(280.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isFromMe) 16.dp else 4.dp,
                            bottomEnd = if (isFromMe) 4.dp else 16.dp
                        )
                    ),
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (isFromMe) 16.dp else 4.dp,
                    bottomEnd = if (isFromMe) 4.dp else 16.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = if (isFromMe) ApplicationColor.Orange else ApplicationColor.Green
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        "Hello, How are you?",
                        color = if (isFromMe) ApplicationColor.White else ApplicationColor.Black
                    )
                    Box(
                        contentAlignment = if (isFromMe) Alignment.CenterStart else Alignment.CenterEnd // ✅ Moves text to the right
                    ) {
                        Text(
                            text = "5:30 PM",
                            color = if (isFromMe) ApplicationColor.White else ApplicationColor.Black,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = if (isFromMe) TextAlign.Start else TextAlign.End, // ✅ Text aligns properly
                        )
                    }
                }
            }
        }
    }


}