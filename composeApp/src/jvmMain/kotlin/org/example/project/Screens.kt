package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.project.entity.User

enum class Screens {
    REGISTRATION,
    LOGIN,
    MAIN,
    ACCOUNT,
    FORGET
}


class Status(screens: Screens)  {
    var screens by mutableStateOf(screens)
    var userInfo by mutableStateOf(User("", "", ""))
}
