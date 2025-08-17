package com.chinky.family.presentation.ui.networkCall.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinky.family.presentation.ui.networkCall.ApiState
import com.chinky.family.presentation.viewModels.UserViewModel

@Composable
fun DisplayUserList(viewModel: UserViewModel = hiltViewModel()) {
    val userState = viewModel.users.value   // observe the State<ApiState>
    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (userState) {
            is ApiState.Loading -> CircularProgressIndicator()
            is ApiState.Failure -> Text(
                text = userState.error?.message ?: "No Data Found",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
            is ApiState.Success -> LazyColumn {
                items(userState.data.size) { index ->
                    UserItem(
                        user = userState.data[index],
                        onDeleteClick = { viewModel.deleteUser(index) }
                    )
                }
            }
            ApiState.Empty -> Text(text = "No users available")
        }
    }
}
