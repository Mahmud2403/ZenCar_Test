package com.example.zencar_test.domain.repository

import com.example.zencar_test.domain.model.User
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getAllUsers(): Flow<List<User>>
    suspend fun getUserByName(name: String): Flow<User>
    suspend fun deleteUser(userId: Int)
    suspend fun getUserById(userId: Int): Flow<User>
    suspend fun updateUser(user: User)
    suspend fun getUsersCreatedAfter(date: String): Flow<List<User>>
}