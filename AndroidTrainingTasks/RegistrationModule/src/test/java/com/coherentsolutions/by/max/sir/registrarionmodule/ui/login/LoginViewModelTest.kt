package com.coherentsolutions.by.max.sir.registrarionmodule.ui.login

import com.coherentsolutions.by.max.sir.registrarionmodule.R
import com.coherentsolutions.by.max.sir.registrarionmodule.data.LoginDataSource
import com.coherentsolutions.by.max.sir.registrarionmodule.data.LoginRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class LoginViewModelTest {

    private lateinit var viewModel:LoginViewModel
    @Before
    fun setUp() {
        viewModel=LoginViewModel(LoginRepository(LoginDataSource()))
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getLoginResult() {
    }

    @Test
    fun login() {
        viewModel.login("hello","world")
        assertEquals(viewModel.loginResult.value,LoginResult(error = R.string.login_failed))
    }
}