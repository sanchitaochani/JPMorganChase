package com.example.jpmc.nycschools.dagger

import com.example.jpmc.nycschools.detail.DetailFragment
import com.example.jpmc.nycschools.MainApplication
import com.example.jpmc.nycschools.schools.SchoolsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun appModule(module: AppModule): Builder
        fun build(): AppComponent
    }

    fun inject(application: MainApplication)
    fun inject(fragment: SchoolsFragment)
    fun inject(fragment: DetailFragment)
}