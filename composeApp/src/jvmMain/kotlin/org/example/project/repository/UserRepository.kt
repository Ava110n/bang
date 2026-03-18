package org.example.project.repository

import org.example.project.entity.User
import org.example.project.errors.NoSuchUserException
import org.example.project.errors.WrongPasswordException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class UserRepository {
    val con: Connection

    constructor(url: String, user: String, password: String) {
        con = DriverManager.getConnection(url, user, password)
    }


    fun initTables() {
        val sql = """
            CREATE TABLE IF NOT EXISTS users (
	        id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	        login varchar(255) NOT NULL UNIQUE,
	        name varchar(255) NOT NULL,
            password text NOT NULL
            );
        """.trimIndent()
        try {
            con.createStatement().use {
                it.execute(sql)
            }
        } catch (e: SQLException) {
            println("init tables $e")
        }
    }

    fun addUser(user: User): Boolean {
        val sql = """
            INSERT INTO users (login, name, password)
            VALUES (?, ?, ?)
        """.trimIndent()

        try {
            con.prepareStatement(sql).use {
                it.setString(1, user.login)
                it.setString(2, user.name)
                it.setString(3, user.password)
                return it.execute()
            }
        } catch (e: SQLException) {
            println(e)
        }
        return false
    }

    fun updatePassword(user: User): Boolean {
        val sql = """
            UPDATE users
            SET password = ?
            WHERE login = ?;
        """.trimIndent()
        try {
            con.prepareStatement(sql).use {
                it.setString(1, user.password.trim())
                it.setString(2, user.login.trim())
                println(it.toString())
                return it.executeUpdate() > 0
            }
        } catch (e: SQLException) {
            println(e)
        }
        return false
    }

    fun login(user: User): User {
        val sql = """
            SELECT login, name, password = ?
            FROM users
            WHERE login = ?
            LIMIT 1
        """.trimIndent()
        try {
            con.prepareStatement(sql).use { it1 ->
                it1.setString(1, user.password)
                it1.setString(2, user.login)
                it1.executeQuery().use {
                    if (it.next()) {
                        if (!it.getBoolean(3)) {
                           throw WrongPasswordException()
                        }

                        return User(
                            it.getString(1),
                            it.getString(2),
                            ""
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            println(e)
        }
        throw NoSuchUserException(user.login)
    }
}
