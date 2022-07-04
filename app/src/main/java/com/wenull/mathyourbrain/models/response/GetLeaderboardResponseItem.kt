package com.wenull.mathyourbrain.models.response


import com.google.gson.annotations.SerializedName

data class GetLeaderboardResponseItem(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("games")
    val games: List<String>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("username")
    val username: String
)