package com.wenull.mathyourbrain.models.basic

data class Question(
    val question: String,
    val options: List<String>,
    val correctOption: Int,
    val rating: Int,
)
