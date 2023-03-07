package com.example.jpmc.nycschools.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class for School object.
 */
@Parcelize
data class School(
    @SerializedName("school_name")
    val name: String,
    @SerializedName("overview_paragraph")
    val description: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("school_email")
    val email: String?,
    @SerializedName("city")
    val city: String,
    @SerializedName("dbn")
    val dbn: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("location")
    val location: String?,
    @SerializedName("extracurricular_activities")
    val extraCurrActivities: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("finalgrades")
    val grades: String?
    ) : Parcelable
