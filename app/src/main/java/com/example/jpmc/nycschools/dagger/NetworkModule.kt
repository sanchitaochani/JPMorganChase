package com.example.jpmc.nycschools.dagger

import com.example.jpmc.nycschools.network.RetrofitService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module that provides retrofit client.
 */
@Module
class NetworkModule {
    @Provides
    fun provideService(): RetrofitService {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://data.cityofnewyork.us/resource/")
            .build()
        return retrofit.create(RetrofitService::class.java)
    }
}