package com.example.zencar_test.data.repository

import android.util.Log
import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.ZenCarDao
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.RegistrationUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RegistrationUserRepositoryImpl @Inject constructor(
    private val userToEntityMapper: BaseMapper<User, UserEntity>,
    private val dao: ZenCarDao,
): RegistrationUserRepository {
    override suspend fun insertUser(user: User) {
        try {
            dao.insertUser(userToEntityMapper.map(user))
            Log.d("insert", "User added successfully: $user")
        } catch (e: Exception) {
            Log.e("insert", "Error adding user: ${e.message}")
        }
    }
    override suspend fun deleteUser(user: User) {
        dao.deleteUser(userToEntityMapper.map(user))
    }
}