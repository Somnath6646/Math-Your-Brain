package com.wenull.mathyourbrain.repo.base

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.qrate.android.models.utils.DataState
import com.wenull.mathyourbrain.models.basic.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.ConnectException

open class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Flow<DataState<T?>> = flow {
        emit(DataState.Loading)
        println("SAFE API CALLING")
        try {
            val response = apiCall()
            Log.i("Response", response.body().toString())
            if (response.isSuccessful) {
                emit(DataState.Success(response.body()))
            } else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type

                val errorResponse: ErrorResponse? =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                println("NOT SUCCESSFUL $errorResponse")
                if (errorResponse != null) {
                    emit(DataState.Error(errorResponse.error))
                }
            }

        } catch (e: Exception) {
            if (e is ConnectException) {
                emit(DataState.Error("No network connection"))
            } else {
                println("ERRORRRR")
                e.printStackTrace()
//                emit(DataState.Error("ERROR"))
            }
        }

    }
}
