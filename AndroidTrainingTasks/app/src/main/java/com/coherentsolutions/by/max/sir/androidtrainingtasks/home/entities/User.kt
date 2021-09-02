package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities

data class User(
    var userId: Long = 0L,
    var name: String = "",
    var age: Long = 0L,
    var nick: String = name,
    var surname: String = "",
    var username: String = ""
)
