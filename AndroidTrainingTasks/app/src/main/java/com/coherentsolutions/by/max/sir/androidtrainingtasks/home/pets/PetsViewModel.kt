package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.pets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.Pet

class PetsViewModel : ViewModel() {

    private val _pets = MutableLiveData<List<Pet>>()   //TODO(dataabase.getAllPets())
    val pets: LiveData<List<Pet>>
        get() = _pets
}