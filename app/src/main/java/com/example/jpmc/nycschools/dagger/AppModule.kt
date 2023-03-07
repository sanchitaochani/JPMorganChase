package com.example.jpmc.nycschools.dagger

import android.app.Application
import com.example.jpmc.nycschools.MainApplication
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule constructor(private val application: MainApplication) {
    @Provides
    @Singleton
    fun getApplication(): Application = application
}