package com.example.zencar_test.domain.repository

import com.example.zencar_test.domain.model.User

interface LoginRepository {
    suspend fun getUserByName(name: String): User?
}