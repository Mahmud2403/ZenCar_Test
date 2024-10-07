package com.example.zencar_test.data.repository

import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.ZenCarDao
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userEntityToUserMapper: BaseMapper<UserEntity, User>,
    private val dao: ZenCarDao,
): LoginRepository {
    override suspend fun getUserByName(name: String): User? {
        val user = dao.getUserByName(name)

        return user?.let { userEntityToUserMapper.map(it) }
    }
}