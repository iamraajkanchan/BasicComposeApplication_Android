package com.chinky.family.presentation.ui.jayantTutorial

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinky.family.domain.model.User
import com.chinky.family.domain.state.UserApiState
import com.chinky.family.domain.utils.printLogcat
import com.chinky.family.presentation.ui.common.AppTopBar
import com.chinky.family.presentation.ui.theme.ApplicationColor
import com.chinky.family.presentation.ui.theme.ApplicationTheme
import com.chinky.family.presentation.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JTutorialPart5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme.HomeApplicationTheme {
                Scaffold(
                    topBar = { AppTopBar("Part 5 Tutorial", this) }
                ) {
                    ShowUserList(it)
                }
            }
        }
    }

    @Composable
    fun ShowUserList(paddingValues: PaddingValues, viewModel : UserViewModel = hiltViewModel()) {
        LaunchedEffect(Unit) {
            viewModel.loadUsers()
        }
        var users: List<User> = emptyList()
        val userState = viewModel.users.value
        JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "userState :  + $userState")
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            when(userState) {
                is UserApiState.Success -> {
                    JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "Data Size :  + ${userState.data.size}")
                    users = userState.data
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues).background(color = ApplicationColor.Red),
                        content = {
                            items(users.size) {
                                ListRow(users[it])
                            }
                        }
                    )
                }
                is UserApiState.Failure -> {
                    JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "Error :  + ${userState.error?.message}")
                    Toast.makeText(LocalContext.current, userState.error.toString(), Toast.LENGTH_SHORT).show()
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No Data Found")
                    }
                }
                is UserApiState.Loading -> {
                    JTutorialPart6Activity::class.java.printLogcat(Thread.currentThread().stackTrace[2], "Loading")
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                UserApiState.Empty -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No Data Found")
                    }
                }
            }
        }
    }

    @Composable
    fun ListRow(user: User) {
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = Icons.Filled.Person ,
                        contentDescription = "Person",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                    Column {
                        Row(modifier = Modifier
                            .padding(4.dp)
                            .clickable { copyUser(context, user.name) }) {
                            Text("Name: ")
                            Text(user.name)
                        }
                        Row(modifier = Modifier
                            .padding(4.dp)
                            .clickable { sendEmail(context, user.email) }) {
                            Text("Email: ")
                            Text(user.email)
                        }
                        Row(modifier = Modifier
                            .padding(4.dp)
                            .clickable { dialPhone(context, user.phone) }) {
                            Text("Phone: ")
                            Text(user.phone)
                        }
                    }
                }
            }
        }
    }

    private fun copyUser(context : Context, userName: String) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clipData = ClipData.newPlainText("user", userName)
        clipboardManager.setPrimaryClip(clipData)
    }

    private fun sendEmail(context: Context, email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }
        if (emailIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(emailIntent)
        } else {
            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dialPhone(context: Context, phone: String) {
        val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        if (phoneIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(phoneIntent)
        } else {
            Toast.makeText(context, "No phone app found", Toast.LENGTH_SHORT).show()
        }
    }
}