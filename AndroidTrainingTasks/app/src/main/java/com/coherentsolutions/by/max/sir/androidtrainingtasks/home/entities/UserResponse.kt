package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(tableName = "users_table")
data class UserResponse(
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    //TODO thinking about(" @PrimaryKey(autoGenerate = true) ") in this context of usage
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "user_status")
    val userStatus: Int,
    @ColumnInfo(name = "username")
    val username: String
) : Serializable, Parcelable