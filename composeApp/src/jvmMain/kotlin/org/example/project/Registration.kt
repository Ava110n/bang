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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun registration(status:  MutableState<Windows>, db: DataBase) {

    if(status.value != Windows.REGISTRATION)
        return


    var name by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirm by remember { mutableStateOf("") }
    Box(modifier = Modifier.background(Color.LightGray)) {
        TextButton(onClick = {status.value = Windows.AUTHORIZATION})
            { Text("Назад") }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name, onValueChange = { newText -> name = newText },
                label = { Text("Имя") })
            TextField(
                value = login, onValueChange = { newText -> login = newText },
                label = { Text("Логин") })

            TextField(
                value = password, onValueChange = { newText -> password = newText },
                visualTransformation = PasswordVisualTransformation('*'),
                label = { Text("Пароль") })

            TextField(
                value = passwordConfirm, onValueChange = { newText -> passwordConfirm = newText },
                visualTransformation = PasswordVisualTransformation('*'),
                label = { Text("Подтвердите пароль") })

            TextButton(onClick = {db.insert(name, login, password)},
                shape = RectangleShape) {
                Text("Зарегистрироваться")
            }
        }
    }
}