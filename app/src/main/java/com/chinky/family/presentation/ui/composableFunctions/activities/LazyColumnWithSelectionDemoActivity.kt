package com.chinky.family.presentation.ui.composableFunctions.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.chinky.family.domain.model.Item
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme

class LazyColumnWithSelectionDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    DisplayLazyColumn(padding)
                }
            }
        }
    }

    @Composable
    private fun DisplayLazyColumn(padding: PaddingValues) {
        val programmingItems = getProgrammingItems()
        val selectedItem = remember { mutableIntStateOf(-1) }
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(programmingItems.size) { index ->
                    val isSelected = selectedItem.intValue == index
                    ItemRowLayout(
                        programmingItems[index],
                        isSelected,
                        Modifier
                            .clickable {
                                selectedItem.intValue = if (isSelected) -1 else index
                            }
                            .fillMaxSize()
                            .padding(5.dp)
                    )
                }
            }
        )
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun ItemRowLayout(
        item: Item,
        isSelected: Boolean,
        modifier: Modifier
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                // containerColor = if (isSelected) ApplicationColor.Purple80 else ApplicationColor.White
                containerColor = ApplicationColor.White,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 4.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                2.dp,
                if (isSelected) ApplicationColor.Orange else ApplicationColor.Transparent
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                GlideImage(
                    model = item.imageUrl,
                    contentDescription = item.description,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(50.dp)
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(item.title, fontWeight = FontWeight.Bold)
                    Text(item.description, fontWeight = FontWeight.Medium)
                }
            }
        }
    }

    private fun getProgrammingItems(): List<Item> {
        val items = mutableListOf<Item>()
        val java = Item(
            1,
            "Java",
            "Java is a high level, object-oriented programming language",
            "https://cdn.iconscout.com/icon/free/png-256/free-java-logo-icon-download-in-svg-png-gif-file-formats--wordmark-programming-language-pack-logos-icons-1174953.png?f=webp"
        )
        val python = Item(
            2,
            "Python",
            "Python is a high-level, interpreted programming language",
            "https://cdn.iconscout.com/icon/free/png-256/free-python-2-226051.png?f=webp"
        )

        val kotlin = Item(
            3,
            "Kotlin",
            "Kotlin is a modern programming language for Android development",
            "https://upload.wikimedia.org/wikipedia/commons/7/74/Kotlin_Icon.png"
        )

        val javascript = Item(
            4,
            "JavaScript",
            "JavaScript is the language of the web",
            "https://cdn.iconscout.com/icon/free/png-256/free-javascript-1-225993.png?f=webp"
        )

        val cpp = Item(
            5,
            "C++",
            "C++ is a powerful general-purpose programming language",
            "https://cdn.iconscout.com/icon/free/png-256/free-c-4-226082.png?f=webp"
        )

        val swift = Item(
            6,
            "Swift",
            "Swift is a programming language developed by Apple",
            "https://cdn.iconscout.com/icon/free/png-256/free-swift-21-1175088.png?f=webp"
        )

        val go = Item(
            7,
            "Go",
            "Go is an open source programming language developed at Google",
            "https://cdn.iconscout.com/icon/free/png-256/free-go-76-1175167.png?f=webp"
        )

        val ruby = Item(
            8,
            "Ruby",
            "Ruby is a dynamic, open source programming language",
            "https://cdn.iconscout.com/icon/free/png-256/free-ruby-47-1175102.png?f=webp"
        )
        items.add(java)
        items.add(python)
        items.add(kotlin)
        items.add(javascript)
        items.add(cpp)
        items.add(swift)
        items.add(go)
        items.add(ruby)
        items.add(java)
        items.add(python)
        items.add(kotlin)
        items.add(javascript)
        items.add(cpp)
        items.add(swift)
        items.add(go)
        items.add(ruby)
        return items
    }
}