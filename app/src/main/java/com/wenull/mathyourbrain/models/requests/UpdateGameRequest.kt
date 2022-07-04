package com.wenull.mathyourbrain.models.requests


import com.google.gson.annotations.SerializedName

data class UpdateGameRequest(
    @SerializedName("completedTime")
    val completedTime: Int,
    @SerializedName("correctQuestions")
    val correctQuestions: List<String>,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("resigned")
    val resigned: Boolean,
    @SerializedName("scoredPoints")
    val scoredPoints: Int
)