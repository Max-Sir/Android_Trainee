package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Pet(
    val category: Category,
    val id: Int,
    val name: String,
    val photoUrls: List<String>,
    val status: String,
    val tags: List<Tag>
) : Parcelable, Serializable