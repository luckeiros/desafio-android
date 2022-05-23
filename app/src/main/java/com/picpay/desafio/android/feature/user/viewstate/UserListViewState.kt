package com.picpay.desafio.android.feature.user.viewstate

import androidx.lifecycle.LiveData
import com.picpay.desafio.android.commons.model.User

class UserListViewState(
    val userListState: LiveData<UserListState>
)

sealed class UserListState{
    object Loading : UserListState()
    object Error : UserListState()
    data class Display(val userList: List<User>) : UserListState()
}