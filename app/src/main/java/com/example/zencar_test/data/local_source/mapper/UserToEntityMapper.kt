package com.example.zencar_test.data.local_source.mapper

import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.domain.model.User
import javax.inject.Inject

class UserToEntityMapper @Inject constructor(

): BaseMapper<User, UserEntity> {
    override fun map(from: User) =
        UserEntity(
            id = from.id,
            img = from.img,
            name = from.name,
            birthday = from.birthday,
            password = from.password,
            dateCreated = from.dateCreated,
            isCurrent = from.isCurrent,
        )
}