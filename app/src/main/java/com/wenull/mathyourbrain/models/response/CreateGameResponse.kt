package com.wenull.mathyourbrain.models.response


import com.google.gson.annotations.SerializedName

data class CreateGameResponse(
    @SerializedName("completedTime")
    val completedTime: Int,
    @SerializedName("correctQuestions")
    val correctQuestions: List<Int>,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("questions")
    val questions: List<Question>,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("resigned")
    val resigned: Boolean,
    @SerializedName("scoredPoints")
    val scoredPoints: Int,
    @SerializedName("thresholdScore")
    val thresholdScore: Int,
    @SerializedName("totalPoints")
    val totalPoints: Int,
    @SerializedName("totalQuestions")
    val totalQuestions: Int,
    @SerializedName("totalTime")
    val totalTime: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("__v")
    val v: Int
)