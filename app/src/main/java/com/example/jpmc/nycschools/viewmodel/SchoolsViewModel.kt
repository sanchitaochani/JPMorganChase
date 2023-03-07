package com.example.jpmc.nycschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpmc.nycschools.data.DetailScreenUiModel
import com.example.jpmc.nycschools.data.ResponseWrapper
import com.example.jpmc.nycschools.data.School
import com.example.jpmc.nycschools.data.Scores
import com.example.jpmc.nycschools.network.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Shared VM between Fragments
 * (since I'm using sharing common school data between both
 * Fragments. If I were to only display
 * SAT scores on the second screen, I would
 * keep separate VMs and repos since they shouldn't
 * be aware of each other's data.)
 */
class SchoolsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    /**
     * Mutable Live Data to hold result.
     */
    private val _schoolsListResult = MutableLiveData<ResponseWrapper>()
    private val _detailScreenUiModel = MutableLiveData<DetailScreenUiModel>()

    /**
     * Public Live Data that is exposed.
     */
    val schoolsListResult: LiveData<ResponseWrapper> = _schoolsListResult
    val detailScreenUiModel: LiveData<DetailScreenUiModel> = _detailScreenUiModel

    private fun fetchListOfSchools() {
        _schoolsListResult.postValue(ResponseWrapper.Loading)
        viewModelScope.launch {
            try {
                val response = repository.getSchoolsList()
                response.enqueue(object: Callback<List<School>> {
                    override fun onResponse(
                        call: Call<List<School>>,
                        response: Response<List<School>>
                    ) {
                        val responseBody = response.body()
                        if (response.isSuccessful && responseBody != null) {
                            Log.i(TAG, responseBody.toString())
                            _schoolsListResult.postValue(ResponseWrapper.Success(responseBody))
                        }
                    }

                    override fun onFailure(call: Call<List<School>>, t: Throwable) {
                        // for debugging purposes only
                        Log.i(TAG, t.message.toString())
                        _schoolsListResult.postValue(ResponseWrapper.Error(ERROR_MSG))
                    }

                })
            } catch(e: Exception) {
                Log.e(TAG, e.message.toString())
                _schoolsListResult.postValue(ResponseWrapper.Error(ERROR_MSG))
            }
        }
    }

    fun getSatScores(school: School) {
        // add loading state
        viewModelScope.launch {
            try {
                val response = repository.getScores(school.dbn)
                response.enqueue(object : Callback<List<Scores>> {
                    override fun onResponse(call: Call<List<Scores>>, response: Response<List<Scores>>) {
                        val responseBody = response.body()
                        if (response.isSuccessful && responseBody != null) {
                            val detailScreenModel = if (responseBody.isEmpty()) {
                                DetailScreenUiModel.toUiModel(school, null)
                            } else {
                                DetailScreenUiModel.toUiModel(school, responseBody[0])
                            }
                            _detailScreenUiModel.postValue(detailScreenModel)
                        }
                    }

                    override fun onFailure(call: Call<List<Scores>>, t: Throwable) {
                        Log.i(TAG,t.message.toString())
                    }

                })
            } catch(e: Exception) {
                Log.e(TAG, e.message.toString())
                // add error handling for this screen
            }
        }
    }

    init {
        fetchListOfSchools()
    }

    companion object {
        private const val TAG = "Schools View Model"
        private const val ERROR_MSG = "Something strange happened, keep calm and try again"
    }
}