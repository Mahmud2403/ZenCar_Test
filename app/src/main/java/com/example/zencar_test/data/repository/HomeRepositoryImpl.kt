package com.example.zencar_test.data.repository

import android.util.Log
import com.example.zencar_test.TAG
import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.room.ZenCarDao
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val entityToUserMapper: BaseMapper<UserEntity, User>,
    private val userEntityToUserMapper: BaseMapper<User, UserEntity>,
    private val dao: ZenCarDao,
) : HomeRepository {
    override suspend fun getAllUsers(): Flow<List<User>> = flow {
        val users = dao.getAllUsers().map { userEntity ->
            entityToUserMapper.map(userEntity)
        }
        emit(users)
    }

    override suspend fun getUserByName(name: String): Flow<User> = flow {
        val user = dao.getUserByName(name)
        emit(entityToUserMapper.map(user))
    }

    override suspend fun getUserById(userId: Int): Flow<User> = flow {
        val user = dao.getUserById(userId)
        emit(entityToUserMapper.map(user))
    }


    override suspend fun deleteUser(userId: Int) {
        dao.deleteUserById(userId)
    }

    override suspend fun updateUser(user: User) {
        val userEntity = userEntityToUserMapper.map(user)
        dao.updateUser(userEntity)
    }

    override suspend fun getUsersCreatedAfter(date: String): Flow<List<User>> = flow {
        val users = dao.getAllUsers()
            .filter { userEntity -> userEntity.dateCreated > date }
            .map { entityToUserMapper.map(it) }
        emit(users)
    }
}