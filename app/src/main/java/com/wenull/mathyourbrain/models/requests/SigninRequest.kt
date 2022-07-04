package com.wenull.mathyourbrain.models.requests


import com.google.gson.annotations.SerializedName

data class SigninRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)