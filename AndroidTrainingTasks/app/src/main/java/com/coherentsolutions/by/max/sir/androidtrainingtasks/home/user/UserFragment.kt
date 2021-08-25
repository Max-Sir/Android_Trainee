package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.UserFragmentBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.utils.AsteriskPasswordTransformationMethod
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson

class UserFragment : Fragment() {

    companion object {
        fun newInstance(args:Bundle?):UserFragment{
            val fragment = UserFragment()
            fragment.arguments=args
            return fragment
        }
    }

    private lateinit var preferences: SharedPreferences
    private lateinit var viewModel: UserViewModel

//    private lateinit var arguments:UserFragmentArgs

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preferences= context?.getSharedPreferences("USER", Context.MODE_PRIVATE)!!

        val user:User=Gson().fromJson(preferences.getString("USER", ""),User::class.java) as User
        //val argumentsUser=UserFragmentArgs.fromBundle(arguments!!)
        //val user= argumentsUser.toBundle().get("USER") as User
        val viewModelFactory:UserViewModelFactory= UserViewModelFactory(user)
        val viewModel=ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)
        val binding:UserFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.user_fragment,container,false)
        //binding.user=user
        binding.user=viewModel.user.value



        //binding.passwordEditText.setText(user.password)

        return binding.root
    }



}