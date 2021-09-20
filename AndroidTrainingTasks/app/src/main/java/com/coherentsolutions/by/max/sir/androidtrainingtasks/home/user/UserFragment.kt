package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
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

class UserFragment : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    private val lifecycleOwner = this

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewModel.eventDeleteUser.observe(viewLifecycleOwner, { state ->
            Log.i(SERVER_TAG, "OBSERVE ${state.name}")
            when (state) {
                DELETE_SUCCEED -> {
                    Log.i(SERVER_TAG, "OBSERVE SUCCESSFUL")

                    Toast.makeText(
                        activity,
                        getString(R.string.user) + getString(R.string.was_delete_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                    activity?.finish()
                    startActivity(Intent(context, LoginActivity::class.java))
                }
                DELETE_FAILED -> {
                    Log.i(SERVER_TAG, "OBSERVE FAILED")
                    Toast.makeText(
                        activity,
                        getString(R.string.user) + getString(R.string.delete_operation_was_failed_try_again),
                        Toast.LENGTH_SHORT
                    ).show()

                }
                NON_CALLED_DELETE_EVENT -> {
                    Log.i(SERVER_TAG, "non delete")

                }
                else -> {
                    Log.i(SERVER_TAG, "OBSERVE error")

                    throw IllegalArgumentException("${UserViewModel.Companion.State::class.simpleName} Enum exception")
                }

            }
        })

        Log.i(INFO_TAG, "ON CREATE VIEW user fragment")

        Log.i(INFO_TAG, "User loaded to fragment")
        Log.i(SERVER_TAG, "UPDATING USER")

        Log.v(SERVER_TAG, "USER UPDATED SUCCESSFULLY")
        val binding: UserFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.user_fragment, container, false)
        binding.userViewModel = viewModel

        Log.i(INFO_TAG, "binded layoyout and fragment inflated")


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.delete_user_menu_item)
        inflater.inflate(R.menu.options_user_fragment_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_user_menu_item -> AlertDialog.Builder(activity)
                .setTitle(getString(R.string.user_delete_alert_dialog_title))
                .setMessage(getString(R.string.alert_dialog_title_delete_user))
                .setPositiveButton(getString(R.string.yes)) { dialog, id ->
                    viewModel.deleteUser()
                    dialog.cancel()
                }
                .setNegativeButton(getString(R.string.no)) { dialog, id ->
                    dialog.cancel()
                }
                .create()
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}