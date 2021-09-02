package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.pets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.PetsFragmentBinding

class PetsFragment : Fragment() {


    private lateinit var viewModel: PetsViewModel
    private lateinit var viewModelFactory: PetsViewModelFactory
    private lateinit var binding: PetsFragmentBinding

    companion object {
        fun newInstance() = PetsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.pets_fragment, container, false)
        viewModelFactory = PetsViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(PetsViewModel::class.java)
        return binding.root
    }


}