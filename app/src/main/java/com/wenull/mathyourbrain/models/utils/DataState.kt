package com.qrate.android.models.utils

sealed class DataState<out R> {
    data class Success<out T>(val data: T): DataState<T>()
    data class Error(val error: String): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}