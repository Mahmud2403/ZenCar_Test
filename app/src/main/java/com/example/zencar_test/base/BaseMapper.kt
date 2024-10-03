package com.example.zencar_test.base

interface BaseMapper<FROM, TO> {
    fun map(from: FROM): TO
}