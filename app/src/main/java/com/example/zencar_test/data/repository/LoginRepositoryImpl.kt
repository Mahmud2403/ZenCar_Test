package com.example.zencar_test.data.repository

import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.room.ZenCarDao
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val entityToUserMapper: BaseMapper<UserEntity, User>,
    private val userEntityToUserMapper: BaseMapper<User, UserEntity>,
    private val dao: ZenCarDao,
): LoginRepository {
    override suspend fun getUserByName(name: String): Flow<User> = flow{
        val user = dao.getUserByName(name)
        emit(entityToUserMapper.map(user))
    }

    override suspend fun updateUser(user: User) {
        val userEntity = userEntityToUserMapper.map(user)
        dao.updateUser(userEntity)
    }
}