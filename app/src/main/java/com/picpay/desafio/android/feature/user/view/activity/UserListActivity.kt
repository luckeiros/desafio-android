package com.picpay.desafio.android.feature.user.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.picpay.desafio.android.R
import com.picpay.desafio.android.commons.extension.observe
import com.picpay.desafio.android.commons.extension.turnGone
import com.picpay.desafio.android.commons.extension.turnVisible
import com.picpay.desafio.android.feature.user.view.adapter.UserListAdapter
import com.picpay.desafio.android.feature.user.viewmodel.UserListViewModel
import com.picpay.desafio.android.feature.user.viewstate.UserListState
import com.picpay.desafio.android.commons.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {

    @Inject
    lateinit var userListViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        observeStates()
        userListViewModel.loadUsers()
    }

    private fun observeStates() {
        with(userListViewModel.viewState) {
            observe(userListState) {
                when (it) {
                    is UserListState.Loading -> showLoading()
                    is UserListState.Display -> showUserList(it.userList)
                    is UserListState.Error -> showError()
                }
            }
        }
    }

    private fun showUserList(userList: List<User>) {
        pbPicPay.turnGone()
        rvPicPayUserList.turnVisible()

        setupAdapter(userList)
    }

    private fun setupAdapter(userList: List<User>) {
        rvPicPayUserList.adapter = UserListAdapter(userList.toMutableList(), this)
    }

    private fun showError() = Toast.makeText(this, "error", Toast.LENGTH_LONG).show()

    private fun showLoading(){
        rvPicPayUserList.turnGone()
        pbPicPay.turnVisible()
    }

}