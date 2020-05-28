package com.tinuade.nougat_home_teach.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.tinuade.nougat_home_teach.R
import com.tinuade.nougat_home_teach.util.openActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        openActivity<OnboardingScreenActivity> {
        }
        finish()


    }
}
