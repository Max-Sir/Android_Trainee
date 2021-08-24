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

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel

    private lateinit var args:UserFragmentArgs

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args=UserFragmentArgs.fromBundle(arguments!!)
        val binding:UserFragmentBinding= DataBindingUtil.inflate(inflater,R.layout.user_fragment,container,false)
        binding.passwordEditText.text="${args.login}"
        return binding.root
    }



}