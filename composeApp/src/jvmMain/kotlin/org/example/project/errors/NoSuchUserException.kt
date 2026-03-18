package org.example.project.errors

import java.lang.Exception

class NoSuchUserException(user: String) : Exception("$user don't exists") {
}