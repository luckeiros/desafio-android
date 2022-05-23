package com.picpay.desafio.android.feature.user.repository

import com.picpay.desafio.android.feature.user.repository.mapper.toUser
import com.picpay.desafio.android.feature.user.repository.service.UserService
import com.picpay.desafio.android.commons.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val retrofit: Retrofit) :
    UserRepository {

    override suspend fun getUsers(): List<User> =
        withContext(Dispatchers.IO) {
            val service = retrofit.create(UserService::class.java)
            service.getUsers().toUser()
        }

}
