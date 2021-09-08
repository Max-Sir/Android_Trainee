package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.Pet

interface PetPersistence {
    fun loadPetList():List<Pet>
    fun savePet(pet:Pet)
    fun savePets(petList:List<Pet>)
}