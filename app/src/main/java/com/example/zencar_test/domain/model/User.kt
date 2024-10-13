package com.example.zencar_test.domain.model

data class User(
    val id: Int = 0,
    val img: String,
    val name: String,
    val birthday: String,
    val password: String,
    val dateCreated: String,
    val isCurrent: Boolean = false,
) {
    companion object {
        val mock = User(
            img = "",
            name = "Sasha",
            birthday = "01.01.2000",
            password = "12345678",
            dateCreated = "10.10.2024"
        )
        val emptyMock = User(
            img = "",
            name = "",
            birthday = "",
            password = "",
            dateCreated = ""
        )
    }
}
