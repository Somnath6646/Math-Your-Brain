package com.wenull.mathyourbrain.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wenull.mathyourbrain.data.local.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDB : RoomDatabase() {

    abstract fun appDao(): AppDAO

    companion object {
        const val DATABASE_NAME = "resapp_database"
    }

}
