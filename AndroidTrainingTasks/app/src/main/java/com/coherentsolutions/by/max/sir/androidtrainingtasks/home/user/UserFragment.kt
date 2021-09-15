package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.UserFragmentBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.UserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.persistence

class UserFragment : Fragment() {

    companion object {
        fun newInstance(args: Bundle?): UserFragment {
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel:UserViewModel by viewModels()


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        Log.i(INFO_TAG,"ON CREATE VIEW user fragment")

        Log.i(INFO_TAG,"User loaded to fragment")
        Log.i(SERVER_TAG,"UPDATING USER")

        viewModel.updateUserAfterSignIn()

        Log.v(SERVER_TAG,"USER UPDATED SUCCESSFULLY")
        val binding: UserFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false)
        binding.userViewModel = viewModel

        Log.i(INFO_TAG,"binded layoyout and fragment inflated")


        return binding.root
    }


}