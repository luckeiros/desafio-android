package com.picpay.desafio.android.feature.user.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.commons.extension.showUserImage
import com.picpay.desafio.android.commons.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*
import java.util.*

class UserListAdapter(
    private val userList: MutableList<User>,
    private val context: Context
) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bindView(userList[position])
        holder.getUserImage(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(user: User) {
            with(itemView) {
                tvName.text = user.name
                tvUsername.text = context.getString(
                    R.string.username, user.username.lowercase(
                        Locale.getDefault()
                    )
                )
            }
        }

        fun getUserImage(user: User) {
            showUserImage(user, itemView.imgUser)
        }

    }

}