package org.example.project.repository
import java.sql.Connection
import java.sql.DriverManager

class User {
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

        val st = con.createStatement()
        st.execute(sql)

    }

}
