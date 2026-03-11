package org.example.project

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

enum class Screens {
    REGISTRATION,
    LOGIN,
    MAIN
}


class Status(screens: Screens)  {
    var screens by mutableStateOf(screens)
}
