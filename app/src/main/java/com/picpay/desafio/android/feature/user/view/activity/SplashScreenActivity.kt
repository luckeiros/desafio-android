package com.picpay.desafio.android.feature.user.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.picpay.desafio.android.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        setOpenTime()
    }

    private fun openUserListActivity() {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setOpenTime() {
        Handler().apply {
            postDelayed({
                openUserListActivity()
            }, 2500)
        }
    }

}