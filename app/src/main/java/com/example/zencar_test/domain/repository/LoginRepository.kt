package com.example.zencar_test.domain.repository

import com.example.zencar_test.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun getUserByName(name: String): Flow<User>
    suspend fun updateUser(user: User)
}