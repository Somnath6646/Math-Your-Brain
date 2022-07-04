package com.wenull.mathyourbrain.data.local.entities

import com.wenull.mathyourbrain.models.basic.Question

data class GameX(
    val userId: String,
    val totalTime: Int,
    val completedTime: Int,
    val totalPoints: Int,
    val scoredPoints: Int,
    val totalQuestions: Int,
    val thresholdScore: Int,
    val resigned: Boolean,
    val rating: Int,
    val correctQuestions: List<String>,
    val createdAt: String

)