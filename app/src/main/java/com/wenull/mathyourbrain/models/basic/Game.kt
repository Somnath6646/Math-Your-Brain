package com.wenull.mathyourbrain.models.basic

data class Game(
    val userId: String,
    val totalTime: Int,
    val completedTime: Int,
    val totalPoints: Int,
    val scoredPoints: Int,
    val questions: List<Question>,
    val totalQuestions: Int,
    val thresholdScore: Int,
    val resigned: Boolean,
    val rating: Int,
    val correctQuestions: List<Question>,
    val createdAt: String

)
