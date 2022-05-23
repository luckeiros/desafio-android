package com.picpay.desafio.android.commons.extension

import com.picpay.desafio.android.commons.model.DefaultError
import java.lang.Exception

fun Exception.toDefaultError() : DefaultError =
    DefaultError(
        errorMessage = this.message.orEmpty()
    )