package com.tinuade.nougat_home_teach.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tinuade.nougat_home_teach.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun goToNext(view:View) {
        val intent =Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
