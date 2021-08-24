package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.UserFragmentBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User

class UserFragment : Fragment() {

    companion object {
        fun newInstance(args:Bundle?):UserFragment{
            val fragment = UserFragment()
            fragment.arguments=args
            return fragment
        }
    }

    private lateinit var viewModel: UserViewModel

//    private lateinit var arguments:UserFragmentArgs

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val argumentsUser=UserFragmentArgs.fromBundle(arguments!!)
        val user= argumentsUser.toBundle().get("USER") as User
        val binding:UserFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.user_fragment,container,false)
        binding.passwordEditText.setText(user.password)
        return binding.root
    }



}