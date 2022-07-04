package com.wenull.mathyourbrain.models.requests


import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)