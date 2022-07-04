package com.wenull.mathyourbrain.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomMasterTable.TABLE_NAME
import com.wenull.mathyourbrain.data.local.entities.User

@Dao
interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM ${User.TABLE_NAME}  ")
    fun getUserFromDB(): LiveData<List<User>>

    @Query("DELETE FROM ${User.TABLE_NAME} ")
    fun deleteAll()

}