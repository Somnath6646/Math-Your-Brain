package com.wenull.mathyourbrain.models.response

import com.google.gson.annotations.SerializedName

data class GetAllAvatarsResponse(
    @SerializedName("avatarLinks")
    val avatars: List<String>,)
