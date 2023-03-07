package com.example.jpmc.nycschools

import android.app.Application
import com.example.jpmc.nycschools.dagger.AppComponent
import com.example.jpmc.nycschools.dagger.AppModule
import com.example.jpmc.nycschools.dagger.DaggerAppComponent

class MainApplication : Application() {
    companion object {
        private lateinit var appComponent: AppComponent

        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(): AppComponent {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        return appComponent
    }
}