package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.project.repository.UserRepository

@Composable
fun account(status: Status, repository: UserRepository) {
    if (status.screens != Screens.ACCOUNT) return
    var balance by remember { mutableStateOf(0.0) }
    Box(modifier = Modifier.background(Color.LightGray)) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row {
                Text("Логин: ${status.userInfo.login}")
            }

            Row {
                Text("Имя: ${status.userInfo.name}")
            }
            Row {
                Text("Баланс $balance")
            }
        }
    }
}