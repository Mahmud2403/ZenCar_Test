package com.example.zencar_test.data.repository

import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.room.ZenCarDao
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.RegistrationUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RegistrationUserRepositoryImpl @Inject constructor(
    private val userToEntityMapper: BaseMapper<User, UserEntity>,
    private val dao: ZenCarDao,
): RegistrationUserRepository {
    override suspend fun insertUser(user: User): Flow<Long> = flow {
        emit(dao.insertUser(userToEntityMapper.map(user)))
    }
}