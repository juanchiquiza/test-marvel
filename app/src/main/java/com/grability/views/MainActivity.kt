package com.grability.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grability.R
import com.grability.views.hero.HeroesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        butLogin.setOnClickListener {
            val intent = Intent(applicationContext, HeroesActivity::class.java)
            startActivity(intent)
        }
    }
}