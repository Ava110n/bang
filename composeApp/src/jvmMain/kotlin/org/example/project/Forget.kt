package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import org.example.project.entity.User
import org.example.project.repository.UserRepository

@Composable
fun forget(status: Status, repository: UserRepository) {
    if (status.screens != Screens.FORGET) return

    var login by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var dropped by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    Box(modifier = Modifier.background(Color.LightGray)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = login, onValueChange = { newText -> login = newText },
                placeholder = { Text("Логин") }, readOnly = dropped,

            )
            TextButton(onClick = {

            }, shape = RectangleShape) {
                Text("Отправить код")
            }

            TextField(
                value = code, onValueChange = {newText -> code = newText},
                placeholder = { Text("Код")}
            )

            TextButton(onClick = {
                if (code == "admin")
                    dropped = true
            }, shape = RectangleShape) {
                Text("Сбросить пароль")
            }
            if (!dropped) return

            TextField(
                value = password, onValueChange = {newText -> password = newText},
                placeholder = { Text("Парол")}
            )

            TextButton(onClick = {
                val us = User(login, "", password)
                repository.updatePassword(us)
                status.screens = Screens.LOGIN

            }, shape = RectangleShape) {
                Text("Подтвердить пароль")
            }
        }
    }
}
