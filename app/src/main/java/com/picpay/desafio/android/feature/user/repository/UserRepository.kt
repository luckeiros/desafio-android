package com.picpay.desafio.android.feature.user.repository

import com.picpay.desafio.android.commons.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}