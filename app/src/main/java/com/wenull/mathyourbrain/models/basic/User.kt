package com.wenull.mathyourbrain.models.basic


import com.google.gson.annotations.SerializedName
import com.wenull.mathyourbrain.data.local.entities.GameX
import com.wenull.mathyourbrain.models.basic.Game

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("games")
    val games: List<GameX>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("username")
    val username: String
)