package com.example.zencar_test.data.local_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.zencar_test.data.local_source.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ZenCarDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM user_entity")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user_entity WHERE name = :name LIMIT 1")
    suspend fun getUserByName(name: String): UserEntity
}