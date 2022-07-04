package com.wenull.mathyourbrain.data.local

import android.app.usage.UsageEvents
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.wenull.mathyourbrain.data.local.entities.GameX
import com.wenull.mathyourbrain.models.basic.Game
import com.wenull.mathyourbrain.models.basic.Question

class Converters {
    @TypeConverter
    fun gamesListToJson(games: List<GameX>) = Gson().toJson(games)

    @TypeConverter
    fun questionsListToJson(question: List<Question>) = Gson().toJson(question)

    @TypeConverter
    fun jsonToGames(json: String) = Gson().fromJson(json, Array<GameX>::class.java).toList()

    @TypeConverter
    fun jsonToQuestions(json: String) = Gson().fromJson(json, Array<Question>::class.java).toList()


}