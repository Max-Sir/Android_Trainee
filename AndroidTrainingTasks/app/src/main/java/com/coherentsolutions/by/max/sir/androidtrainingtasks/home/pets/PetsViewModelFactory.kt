package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.pets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class PetsViewModelFactory(/*TODO(something)*/):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PetsViewModel::class.java)){
            return PetsViewModel(/*TODO(something)*/) as T
        }
        throw IllegalArgumentException("Can not create this ViewModel class")
    }


}