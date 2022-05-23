package com.picpay.desafio.android.feature.user.repository.mapper

import com.picpay.desafio.android.commons.model.User
import com.picpay.desafio.android.commons.model.UserResponse

fun List<UserResponse>.toUser() =
    map { userResponse ->
        User(
            img = userResponse.img,
            name = userResponse.name,
            id = userResponse.id,
            username = userResponse.username
        )
    }