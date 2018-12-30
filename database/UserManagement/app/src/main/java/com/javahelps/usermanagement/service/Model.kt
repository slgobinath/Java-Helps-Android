package com.javahelps.usermanagement.service

object Model {
    data class User(val username: String, val password: String, val name: String, val email: String)
}