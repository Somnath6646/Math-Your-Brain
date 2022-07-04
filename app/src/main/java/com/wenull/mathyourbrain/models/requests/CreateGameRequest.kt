package com.wenull.mathyourbrain.models.requests


import com.google.gson.annotations.SerializedName

data class CreateGameRequest(
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("thresholdScore")
    val thresholdScore: Int,
    @SerializedName("totalQuestions")
    val totalQuestions: Int,
    @SerializedName("totalTime")
    val totalTime: Int
)