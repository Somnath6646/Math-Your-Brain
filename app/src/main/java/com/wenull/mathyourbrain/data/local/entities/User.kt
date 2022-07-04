package com.wenull.mathyourbrain.data.local.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.wenull.mathyourbrain.models.basic.Game

@Entity(tableName = User.TABLE_NAME)
data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("games")
    val games: List<GameX>,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("username")
    val username: String
){
    companion object{
        const val TABLE_NAME = "user"
    }
}