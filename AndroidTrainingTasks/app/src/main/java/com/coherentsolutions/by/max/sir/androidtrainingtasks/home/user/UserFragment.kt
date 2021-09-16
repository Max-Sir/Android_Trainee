package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.UserFragmentBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user.UserViewModel.Companion.State.*
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*

class UserFragment : Fragment() {

    companion object {
        fun newInstance(args: Bundle?): UserFragment {
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: UserViewModel by viewModels()

    private val lifecycleOwner=this

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        Log.i(INFO_TAG, "ON CREATE VIEW user fragment")

        Log.i(INFO_TAG, "User loaded to fragment")
        Log.i(SERVER_TAG, "UPDATING USER")

        viewModel.updateUserAfterSignIn()

        Log.v(SERVER_TAG, "USER UPDATED SUCCESSFULLY")
        val binding: UserFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false)
        binding.userViewModel = viewModel

        Log.i(INFO_TAG, "binded layoyout and fragment inflated")


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.eventDeleteUser.observe(viewLifecycleOwner, { state ->
            when (state) {
                DELETE_SUCCEED -> {
                    Toast.makeText(
                        activity,
                        getString(R.string.user) + username.text + getString(R.string.was_delete_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(context, LoginActivity::class.java))
                    activity?.finish()
                }
                DELETE_FAILED -> {
                    Toast.makeText(
                        activity,
                        getString(R.string.user) + username.text + getString(R.string.delete_operation_was_failed_try_again),
                        Toast.LENGTH_SHORT
                    ).show()

                }
                NON_CALLED_DELETE_EVENT -> {
                }
                else -> {
                    throw IllegalArgumentException("${UserViewModel.Companion.State::class.simpleName} Enum exception")
                }

            }
        })
        super.onViewCreated(view, savedInstanceState)

    }


}