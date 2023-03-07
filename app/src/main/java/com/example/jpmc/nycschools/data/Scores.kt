package com.example.jpmc.nycschools.data

import com.google.gson.annotations.SerializedName

/**
 * Data class for Scores object.
 */
data class Scores(
    @SerializedName("sat_critical_reading_avg_score")
    val readingScore: String?,
    @SerializedName("sat_math_avg_score")
    val mathScore: String?,
    @SerializedName("sat_writing_avg_score")
    val writingScores: String?,
    @SerializedName("num_of_sat_test_takers")
    val noOfTestTakers: String?
)
