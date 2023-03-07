package com.example.jpmc.nycschools.network

import com.example.jpmc.nycschools.data.School
import com.example.jpmc.nycschools.data.Scores
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for Retrofit Service
 */
interface RetrofitService {
    // fetches list of schools
    @GET("s3k6-pzi2.json")
    fun getSchoolsList() : Call<List<School>>

    // fetches SAT scores for given school
    @GET("f9bf-2cp4.json")
    fun getScores(@Query("dbn") value: String?) : Call<List<Scores>>
}