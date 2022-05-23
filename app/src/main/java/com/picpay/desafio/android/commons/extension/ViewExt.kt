package com.picpay.desafio.android.commons.extension

import android.view.View
import com.picpay.desafio.android.R
import com.picpay.desafio.android.commons.model.User
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

fun View.turnVisible() {
    this.visibility = View.VISIBLE
}

fun View.turnGone() {
    this.visibility = View.GONE
}

fun showUserImage(user: User, img: CircleImageView){
    Picasso.get()
        .load(user.img)
        .error(R.drawable.ic_round_account_circle)
        .placeholder(R.drawable.ic_round_account_circle)
        .into(img)
}