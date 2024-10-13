package com.example.zencar_test.data.local_source.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.zencar_test.data.local_source.model.UserEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ZenCarDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM user_entity")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user_entity WHERE name = :name LIMIT 1")
    suspend fun getUserByName(name: String): UserEntity

    @Query("DELETE FROM user_entity WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("SELECT * FROM user_entity WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserEntity

    @Update
    suspend fun updateUser(user: UserEntity)
}