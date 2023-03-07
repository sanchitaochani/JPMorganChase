package com.example.jpmc.nycschools.data

/**
 * UI model for DetailFragment.
 * Ideally I would work with some more data
 * and structure it differently.
 * I wanted to display num of users in a card view
 * as a grid, and display some icons on this page as well
 * but for the sake of time, I kept it super simple.
 */
class DetailScreenUiModel(
    val name: String,
    val description: String,
    val readingScore: String,
    val mathScore: String,
    val writingScore: String,
    val numOfTestTakers: String,
    val location: String?,
    val activities: String?,
    val website: String?,
    val grades: String?
) {
    companion object {
        fun toUiModel(school: School, scores: Scores?): DetailScreenUiModel {
            return DetailScreenUiModel(
                name = school.name,
                description = school.description ?: "",
                readingScore = scores?.readingScore ?: "N/A",
                mathScore = scores?.mathScore ?: "N/A",
                writingScore = scores?.mathScore ?: "N/A",
                numOfTestTakers = scores?.noOfTestTakers ?: "N/A",
                website = school.website,
                activities = school.extraCurrActivities,
                location = school.location,
                grades = school.grades
            )
        }
    }
}