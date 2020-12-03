package com.alex.registryemployers.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "employer_table")
data class Employer (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstNameval : String,
    val lastName : String,
    val age: Int,
    val positionInCompany : String,
    val dateOfEmployment : String
): Parcelable