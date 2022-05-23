package com.picpay.desafio.android.factory

import com.picpay.desafio.android.commons.model.User

fun usersList() =
    listOf(
        User(
            img = "https://randomuser.me/api/portraits/men/1.jpg",
            name = "Lucas Ferreira",
            id = 1,
            username = "@lucasferreira"
        )
    )