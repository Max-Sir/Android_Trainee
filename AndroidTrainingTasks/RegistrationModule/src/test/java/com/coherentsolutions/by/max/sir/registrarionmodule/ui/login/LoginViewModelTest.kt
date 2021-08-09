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
    fun `bad username`(){
        assertEquals(viewModel.isUserNameValid(""),false)
        assertEquals(viewModel.isUserNameValid("Marta"),true)
        assertEquals(viewModel.isUserNameValid("154"),false)
        assertEquals(viewModel.isUserNameValid("154;oig"),false)
        assertEquals(viewModel.isUserNameValid("#Assasin3258"),true)
        assertEquals(viewModel.isUserNameValid("%%Salamaleikum$$"),true)
        assertEquals(viewModel.isUserNameValid("_)154"),false)
        assertEquals(viewModel.isUserNameValid("154_Max"),true)
        assertEquals(viewModel.isUserNameValid("Max.Sir.2001"),true)




    }

    @Test
    fun `incorrect user passwords`(){
        assertEquals(viewModel.isPasswordValid("oghroig"),false)
        assertEquals(viewModel.isPasswordValid("oiPUOHGpo"),false)
        assertEquals(viewModel.isPasswordValid("огрмрщ"),false)
        assertEquals(viewModel.isPasswordValid("ДОЗЩГШзщ213&*"),false)
        assertEquals(viewModel.isPasswordValid("sIfeward*9"),true)
        assertEquals(viewModel.isPasswordValid("Oa0#84"),false)
        assertEquals(viewModel.isPasswordValid("{OI)48ew_j9"),true)


    }

    @Test
    fun `bad password case`() {
        viewModel.login("hello","world")
        assertEquals(viewModel.loginResult.value,LoginResult(error = R.string.login_failed))
    }
}