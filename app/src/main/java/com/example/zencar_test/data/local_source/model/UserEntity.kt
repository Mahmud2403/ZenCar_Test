package com.example.zencar_test.data.local_source.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val img: String?,
    val name: String,
    val birthday: String,
    val password: String,
    val dateCreated: String,
)
