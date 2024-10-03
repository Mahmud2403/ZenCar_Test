package com.example.zencar_test.data.local_source.mapper

import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor(

): BaseMapper<UserEntity, User> {
    override fun map(from: UserEntity) =
        User(
            img = from.img,
            name = from.name,
            birthday = from.birthday,
            password = from.password,
            dateCreated = from.dateCreated,
        )
}