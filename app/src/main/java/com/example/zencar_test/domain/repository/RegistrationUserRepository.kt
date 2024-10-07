package com.example.zencar_test.domain.repository

import com.example.zencar_test.domain.model.User
import kotlinx.coroutines.flow.Flow

interface RegistrationUserRepository {
    suspend fun insertUser(user: User)
    suspend fun deleteUser(user: User)
}