package com.chinky.family.activities.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chinky.family.ui.theme.ApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

class LazyColumnWithPaginationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApplicationTheme.ChildApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    ManualPaginationLazyColumn(padding)
                }
            }
        }
    }

    @Composable
    fun ManualPaginationLazyColumn(padding: PaddingValues) {
        val page = remember { mutableStateOf(1) }
        val loading = remember { mutableStateOf(false) }
        val itemList = remember { mutableStateListOf<String>() }
        val listState = rememberLazyListState()

        LazyColumn(state = listState) {
            items(itemList) { item ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                bottom = WindowInsets.Companion.navigationBars.asPaddingValues()
                                    .calculateBottomPadding()
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(modifier = Modifier.padding(12.dp), text = item)
                    }
                }
            }
            item {
                if (loading.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = WindowInsets.navigationBars.asPaddingValues().calculateTopPadding(),
                                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                            ),
                        contentAlignment = Alignment.Center,
                    ) { CircularProgressIndicator() }
                }
            }
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                .collectLatest { index ->
                    if (!loading.value && index != null && index >= itemList.size - 5) {
                        page.value++
                        loading.value = true
                        delay(2000) // Simulate network delay
                        itemList.addAll(generateFakeData(page.value))
                        loading.value = false
                    }
                }
        }
    }

    private fun generateFakeData(page: Int): List<String> {
        return List(100) { "Item ${(page - 1) * 20 + it + 1}" }
    }
}