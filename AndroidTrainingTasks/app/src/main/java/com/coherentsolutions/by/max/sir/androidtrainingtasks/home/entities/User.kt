package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities

import java.io.Serializable

data class User(
    var username: String = "",
    var password: String = ""
) : Serializable
