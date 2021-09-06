package com.coherentsolutions.by.max.sir.androidtrainingtasks.data

import com.coherentsolutions.by.max.sir.androidtrainingtasks.data.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

@Suppress("UNUSED_PARAMETER")
class LoginDataSource {

    fun login(
        username: String,
        password: String,
        email: String,
        phone: String
    ): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}
