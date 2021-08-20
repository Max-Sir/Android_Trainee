package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login

import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)