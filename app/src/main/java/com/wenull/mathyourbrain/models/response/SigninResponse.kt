package com.wenull.mathyourbrain.models.response


import com.google.gson.annotations.SerializedName
import com.wenull.mathyourbrain.models.basic.User

data class SigninResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("user")
    val user: User
)