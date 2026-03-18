package org.example.project

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview

import org.example.project.repository.UserRepository

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview
fun App() {
    val screen = Status(Screens.LOGIN)
    val repository = UserRepository(
        "jdbc:postgresql://localhost:5432/gang05306",
        "postgres",
        "1111",
    )
    repository.initTables()
    authorization(screen, repository)
    registration(screen, repository)
    account(screen, repository)
    forget(screen, repository)
}