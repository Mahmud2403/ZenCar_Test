package com.example.zencar_test.data.local_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.zencar_test.data.local_source.model.UserEntity


@Dao
interface ZenCarDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}