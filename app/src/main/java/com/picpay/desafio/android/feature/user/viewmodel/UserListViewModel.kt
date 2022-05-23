package com.picpay.desafio.android.feature.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.commons.extension.emit
import com.picpay.desafio.android.commons.extension.safeLaunch
import com.picpay.desafio.android.commons.model.DefaultError
import com.picpay.desafio.android.feature.user.repository.UserRepository
import com.picpay.desafio.android.feature.user.viewstate.UserListState
import com.picpay.desafio.android.feature.user.viewstate.UserListViewState
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val viewState = UserListViewState(
        userListState = MutableLiveData()
    )

    private suspend fun getUsers() {
        val users = repository.getUsers()
        viewState.userListState.emit(UserListState.Display(users))
    }

    fun loadUsers() = safeLaunch(handleError()) {
        viewState.userListState.emit(UserListState.Loading)
        getUsers()
    }

    private fun handleError(error: DefaultError) {
        viewState.userListState.emit(UserListState.Error)
    }
}
