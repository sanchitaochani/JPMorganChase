package com.example.jpmc.nycschools.data

/**
 * Wrapper class for handling response states:
 *  Success, Error and Loading
 */
sealed class ResponseWrapper(
    val schoolData: List<School>? = null,
    val errorMsg: String? = null
) {
    class Success(data: List<School>) : ResponseWrapper(schoolData = data)

    object Loading : ResponseWrapper()

    class Error(msg: String) : ResponseWrapper(errorMsg = msg)
}