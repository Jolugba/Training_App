package com.tinuade.nougat_home_teach.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tinuade.nougat_home_teach.R

class SignUpScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign__up__screen1_)
    }


    fun goToRegisterParent(view: View) {
        val intent =Intent(this,
            ParentSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun goToRegisterTeacher(view: View) {
        val intent =Intent(this,
            TeacherSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}
