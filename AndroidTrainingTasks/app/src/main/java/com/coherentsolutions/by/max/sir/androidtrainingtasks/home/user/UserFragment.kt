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
import androidx.lifecycle.ViewModelProvider
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.UserFragmentBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.UserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.persistence

class UserFragment : Fragment() {

    companion object {
        fun newInstance(args: Bundle?): UserFragment {
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var preferences: SharedPreferences
    private lateinit var viewModel: UserViewModel


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        Log.i(INFO_TAG,"ON CREATE VIEW user fragment")
        val persistence:UserPersistance= persistence<UserPersistance>()
        val user=persistence.loadUser()
        Log.i(INFO_TAG,"User loaded to fragment")
//        val user: User = Gson().fromJson(preferences.getString(USER, ""), User::class.java) as User
        val viewModelFactory = UserViewModelFactory(user)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
        Log.i(INFO_TAG,"user view model builded by factory")
        val binding: UserFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false)
        binding.user = viewModel.user.value

        Log.i(INFO_TAG,"binded layoyout and fragment inflated")


        return binding.root
    }


}