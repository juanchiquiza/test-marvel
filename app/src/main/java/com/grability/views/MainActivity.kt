package com.grability.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grability.R
import com.grability.utils.isValidEmail
import com.grability.views.hero.HeroesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        butLogin.setOnClickListener {
            if(isValidForm()) {
                val intent = Intent(applicationContext, HeroesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isValidForm(): Boolean {
        if(!txtCredential.isValidEmail()) {
            txtCredential.error = getString(R.string.email_error)
            txtCredential.isFocusable = true
            return false
        }
        if(txtPassword.text?.isEmpty()!!) {
            txtPassword.error = getString(R.string.password_error)
            txtPassword.isFocusable = true
            return false
        }
        return true
    }
}