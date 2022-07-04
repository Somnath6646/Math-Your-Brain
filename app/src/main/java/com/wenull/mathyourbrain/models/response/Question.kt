package com.wenull.mathyourbrain.models.response


import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("correctOption")
    val correctOption: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("options")
    val options: List<String>,
    @SerializedName("question")
    val question: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)