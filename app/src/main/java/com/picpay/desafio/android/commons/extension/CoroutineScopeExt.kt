package com.picpay.desafio.android.commons.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.commons.model.DefaultError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

fun CoroutineScope.safeLaunch(
    block: suspend CoroutineScope.() -> Unit,
    errorBlock: (error: DefaultError) -> Unit
) {
    launch {
        try {
            block.invoke(this)
        } catch (e: Exception) {
            errorBlock.invoke(e.toDefaultError())
        }
    }
}

fun ViewModel.safeLaunch(
    block: suspend CoroutineScope.() -> Unit,
    errorBlock: (error: DefaultError) -> Unit
) = viewModelScope.safeLaunch(block, errorBlock)

