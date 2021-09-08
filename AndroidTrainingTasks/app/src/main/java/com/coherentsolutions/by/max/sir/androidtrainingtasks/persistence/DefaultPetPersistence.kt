package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.Pet
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.PetPersistence

class DefaultPetPersistence: PetPersistence {
    override fun loadPetList(): List<Pet> {
        Log.i(INFO_TAG,"SAVE PET CALL")
        TODO("implement in next steps")
    }

    override fun savePet(pet: Pet) {
        Log.i(INFO_TAG,"SAVE single PET CALL")
        TODO("implement in next steps")
    }

    override fun savePets(petList: List<Pet>) {
        Log.i(INFO_TAG,"SAVE PETS (List<Pet> CALL")
        TODO("implement in next steps")
    }
}