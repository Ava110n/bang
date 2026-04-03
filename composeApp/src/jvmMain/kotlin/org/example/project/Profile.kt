package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun profile(status: MutableState<Windows>, db: DataBase) {

    if (status.value != Windows.PROFILE)
        return
    Box(modifier = Modifier.background(Color.LightGray)) {
        TextButton(onClick = {status.value = Windows.AUTHORIZATION})
        { Text("Выход") }
        Text("Добро пожаловать!")
    }

}