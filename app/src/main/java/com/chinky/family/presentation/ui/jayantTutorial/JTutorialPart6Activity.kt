package com.chinky.family.presentation.ui.jayantTutorial

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Outline
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinky.family.domain.model.ToDoItem
import com.chinky.family.domain.state.ToDoState
import com.chinky.family.domain.utils.printLogcat
import com.chinky.family.presentation.ui.common.AppTopBar
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import com.chinky.family.presentation.viewModels.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class JTutorialPart6Activity : ComponentActivity() {
    private var viewModel : TodoViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var addTodo by remember { mutableStateOf(false) }
            ApplicationTheme.HomeApplicationTheme {
                viewModel = hiltViewModel()
                Scaffold(
                    topBar = { AppTopBar("Part 6 Tutorial", this) },
                    floatingActionButton = {
                        IconButton(onClick = {
                            addTodo = true
                        }) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                        }
                    }
                ) {
                    ShowTodoList(it)
                }
            }
            if (addTodo) {
                ShowTodoDialog { addTodo = false }
            }
        }
    }

    @Composable
    fun ShowTodoList(paddingValues: PaddingValues) {
        LaunchedEffect(Unit) {
            viewModel?.loadTodos()
        }
        var todos: List<ToDoItem> = emptyList()
        val userState = viewModel?.todos?.value
        JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "userState :  + $userState")
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            when(userState) {
                is ToDoState.Success -> {
                    JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "Data Size :  + ${userState.data.size}")
                    todos = userState.data
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues).background(color = ApplicationColor.Red),
                        content = {
                            items(todos.size) {
                                ListRow(todos[it])
                            }
                        }
                    )
                }
                is ToDoState.Error -> {
                    JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "Error :  + ${userState.error?.message}")
                    Toast.makeText(LocalContext.current, userState.error.toString(), Toast.LENGTH_SHORT).show()
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No Data Found")
                    }
                }
                is ToDoState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    @Composable
    fun ListRow(todoItem: ToDoItem) {
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(2.dp))
                    .padding(8.dp),
                shape = RoundedCornerShape(2.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier
                        .padding(4.dp)
                        ) {
                        Text("Date: ")
                        Text(todoItem.date)
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)
                        .clickable { copyUser(context, todoItem.title) }) {
                        Text("Title: ")
                        Text(todoItem.title)
                    }
                    Row(modifier = Modifier
                        .padding(4.dp)
                        .clickable { copyUser(context, todoItem.description) }) {
                        Text("Description: ")
                        Text(todoItem.description)
                    }
                }
            }
        }
    }

    @Composable
    private fun ShowTodoDialog(onDismiss: () -> Unit) {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var selectedDate by remember { mutableStateOf("") }
        var showDateDialog by remember { mutableStateOf(false) }
        AlertDialog(
           onDismissRequest = { onDismiss },
            title = { Text("Add a Todo!")},
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        OutlinedTextField(
                            value = selectedDate,
                            onValueChange = { selectedDate = it },
                            label = { Text("Date") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Optional spacing
                        IconButton(
                            onClick = { showDateDialog = true },
                            modifier = Modifier.size(48.dp) // Ensures visibility
                        ) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Date"
                            )
                        }
                    }
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = {Text("Title")}
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = {description = it},
                        label = { Text("Description")}
                    )
                }
            },
            confirmButton = { onDismiss },
            dismissButton = { onDismiss }
        )
        JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "showDateDialog $showDateDialog")
        if (showDateDialog) {
            ReactiveDatePicker()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ReactiveDatePicker() {
        var showDialog by remember { mutableStateOf(true) }
        var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
        val datePickerState = rememberDatePickerState()
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        datePickerState.selectedDateMillis?.let { millis ->
                            selectedDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                        }
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { showDialog = true }) {
                Text("Pick a Date")
            }
            selectedDate?.let {
                Text("Selected Date: ${it.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))}")
            }
        }
    }

    private fun copyUser(context : Context, userName: String) {
        val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("user", userName)
        clipboardManager.setPrimaryClip(clipData)
    }
}