package com.example.jpmc.nycschools.network

import com.example.jpmc.nycschools.data.School
import com.example.jpmc.nycschools.data.Scores
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

class Repository @Inject constructor(
    private val service: RetrofitService
) {
    suspend fun getSchoolsList(): Call<List<School>> {
        val response = withContext(Dispatchers.IO) {
            service.getSchoolsList()
        }
        return response
    }

    suspend fun getScores(value: String?): Call<List<Scores>> {
        val response = withContext(Dispatchers.IO) {
            service.getScores(value)
        }
        return response
    }
}