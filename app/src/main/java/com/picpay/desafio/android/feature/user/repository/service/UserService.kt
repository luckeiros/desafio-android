package com.picpay.desafio.android.feature.user.repository.service

import com.picpay.desafio.android.commons.constants.Constants
import com.picpay.desafio.android.commons.model.UserResponse
import retrofit2.http.GET

interface UserService {
    @GET(Constants.USER_PATH)
    suspend fun getUsers(): List<UserResponse>
}
