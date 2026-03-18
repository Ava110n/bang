package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import org.example.project.entity.User
import org.example.project.repository.UserRepository

@Composable
fun authorization(status: Status, repository: UserRepository) {
    if (status.screens != Screens.LOGIN)
        return
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.background(Color.LightGray)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = login, onValueChange = { newText -> login = newText },
                placeholder = { Text("Логин") })
            TextField(
                value = password, onValueChange = { newText -> password = newText },
                visualTransformation = PasswordVisualTransformation('*'),
                placeholder = { Text("Пароль") })
            /*SecureTextField(state = state,
                placeholder = {Text("Пароль")})*/
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    TextButton(onClick = { status.screens = Screens.REGISTRATION }, shape = RectangleShape) {
                        Text("Создать аккаунт")
                    }
                }
                Column {
                    TextButton(onClick = {
                        val user = repository.login(User(login, "", password))
                        if (user.login == "") return@TextButton

                    }, shape = RectangleShape) {
                        Text("Войти")
                    }
                }
            }
        }
    }

}