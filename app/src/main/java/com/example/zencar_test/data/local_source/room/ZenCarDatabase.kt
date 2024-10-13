package com.example.zencar_test.data.local_source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zencar_test.data.local_source.model.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1,
)

abstract class ZenCarDatabase: RoomDatabase() {
    abstract val dao: ZenCarDao
}