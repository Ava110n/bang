package org.example.project

import java.sql.Connection
import java.sql.DriverManager

class DataBase {
    lateinit var connection: Connection

    constructor() {
        val host = "localhost"
        val port = 5432
        val database = "postgres"
        val url = "jdbc:postgresql://$host:$port/$database"

        val user = "postgres"
        val password = "1111"

        try {
            connection = DriverManager.getConnection(url, user, password)
        } catch (e: Exception) {
            println("NO CONNECTION")
        }
    }

    fun create() {
        //id INTEGER PRIMARY KEY,
        val creating = """
            CREATE TABLE IF NOT EXISTS Users(
                name VARCHAR(255),
                login VARCHAR(255) UNIQUE,
                password TEXT
                )
            """.trimIndent()
        try {
            val statement = connection.createStatement()
            statement.execute(creating)
            println("Succes creating Users")
        } catch (e: Exception) {
            println("CREATE ERROR")
        }
    }

    fun select(login: String, password: String): User? {
        val selecting = """
            SELECT login, password, name
            FROM Users
            WHERE login = ? 
        """.trimIndent()//AND password=?
        lateinit var user: User
        try {
            connection.prepareStatement(selecting).use {
                it.setString(1, login)
                //it.setString(2, password)
                it.executeQuery().use {
                    if (it.next()) {
                        try {
                            user = User(
                                it.getString(1),
                                it.getString(2),
                                it.getString(3)
                            )
                        } finally {
                            //println(it.getString(2))
                            if (user.password != password) {
                                println("wrong password")
                            }
                            else{
                                return user
                            }
                        }
                    } else {
                        println("wrong login")
                    }
                }
            }
        } catch (e: Exception) {
            println("SELECT ERROR")
            println(e.message)
        }
        return null
    }

    fun insert(name: String, login: String, password: String) {
        val inserting = """
            INSERT INTO Users
            (name, login, password)
            VALUES (?,?,?)
        """.trimIndent()
        try {
            connection.prepareStatement(inserting).use {
                it.setString(1, name)
                it.setString(2, login)
                it.setString(3, password)
                it.execute()
            }
        } catch (e: Exception) {
            println("INSERT ERROR")
            println(e.message)
        }
    }

    fun drop() {
        val dropping = """
            DROP DATABASE Users;
        """.trimIndent()
        try {
            connection.prepareStatement(dropping).use {
                it.execute()
            }
        } catch (e: Exception) {
            println("DROP ERROR")
            println(e.message)
        }
    }
}
