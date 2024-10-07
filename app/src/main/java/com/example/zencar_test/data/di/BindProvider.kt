package com.example.zencar_test.data.di

import com.example.zencar_test.base.BaseMapper
import com.example.zencar_test.data.local_source.mapper.EntityToUserMapper
import com.example.zencar_test.data.local_source.mapper.UserToEntityMapper
import com.example.zencar_test.data.local_source.model.UserEntity
import com.example.zencar_test.data.repository.HomeRepositoryImpl
import com.example.zencar_test.data.repository.LoginRepositoryImpl
import com.example.zencar_test.data.repository.RegistrationUserRepositoryImpl
import com.example.zencar_test.domain.model.User
import com.example.zencar_test.domain.repository.HomeRepository
import com.example.zencar_test.domain.repository.LoginRepository
import com.example.zencar_test.domain.repository.RegistrationUserRepository
import com.example.zencar_test.utils.ValidateRegistration
import com.example.zencar_test.utils.ValidateRegistrationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindProvider {

    @Binds
    fun bindUserToEntityMapperMapper(impl: UserToEntityMapper): BaseMapper<User, UserEntity>

    @Binds
    fun bindEntityToUserMapperMapper(impl: EntityToUserMapper): BaseMapper<UserEntity, User>

    @Binds
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindRegistrationRepository(impl: RegistrationUserRepositoryImpl): RegistrationUserRepository

    @Binds
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun binValidateRegistration(impl: ValidateRegistrationImpl): ValidateRegistration
}