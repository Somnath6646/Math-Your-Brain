package com.wenull.mathyourbrain.models.basic

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error")
    val error: String
) {
}