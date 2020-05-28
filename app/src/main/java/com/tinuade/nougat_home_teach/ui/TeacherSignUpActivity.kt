package com.tinuade.nougat_home_teach.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tinuade.nougat_home_teach.R
import com.tinuade.nougat_home_teach.databinding.ActivityTeacherSignUpBinding
import com.tinuade.nougat_home_teach.model.TeacherUsers
import com.tinuade.nougat_home_teach.model.UserType
import com.tinuade.nougat_home_teach.util.showToast

class TeacherSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherSignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var users: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_teacher_sign_up)
        binding.progressBar3.visibility==View.VISIBLE

        //init firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        users = database.getReference("users")

        binding.teacherRegisterButton.setOnClickListener {
            teacherSignUp()
        }


    }

    fun goToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun teacherSignUp() {
        val email = binding.teacherEmailSignUp.text.toString()
        val name = binding.teacherNameSignUp.text.toString()
        val password = binding.teacherPasswordSignUp.text.toString()
        val phoneNumber = binding.teacherSignUpNum.text.toString()
        val subject = binding.addSubject.text.toString()
        val yearsOfExperience = binding.amountCharged.text.toString()

        if (TextUtils.isEmpty(name) && name.length < 5) {
            binding.teacherNameSignUp.error = "enter a valid name"
            binding.teacherNameSignUp.requestFocus()
        } else if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.teacherEmailSignUp.error = "Please Enter a valid Email Address"
            binding.teacherEmailSignUp.requestFocus()
        } else if (TextUtils.isEmpty(email) && !(email.contains("@"))) {
            binding.teacherEmailSignUp.error = "Please Enter valid Email address"
            binding.teacherEmailSignUp.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            binding.teacherPasswordSignUp.error = "Please Enter a valid Password"
            binding.teacherPasswordSignUp.requestFocus()
        } else if (password.isEmpty() || password.length < 8) {
            binding.teacherPasswordSignUp.error = ("Your password must be minimum of 8 characters")
            binding.teacherPasswordSignUp.requestFocus()
        } else if (subject.isEmpty() || subject.length < 2) {
            binding.addSubject.error = ("Enter the subject you want to teach")
            binding.addSubject.requestFocus()
        } else if (yearsOfExperience.isEmpty()) {
            binding.amountCharged.error = ("Enter your years of experience")
            binding.amountCharged.requestFocus()
        } else if (phoneNumber.isEmpty()) {
            binding.teacherSignUpNum.error = ("Enter your phone Number")
            binding.teacherSignUpNum.requestFocus()

        } else {
            binding.progressBar3.visibility==View.VISIBLE
        //register users
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    //save user to the database
                    val teacher = TeacherUsers(name,email,password,phoneNumber,subject,yearsOfExperience,UserType.TEACHER)
                    mAuth.currentUser?.uid?.let { users.child(it) }
                        ?.setValue(teacher)
                        ?.addOnCompleteListener {
                            binding.progressBar3.visibility==View.GONE
                            showToast("Authentication Successful")
                            val intent = Intent(this, TeacherDashBoardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                } else {
                    binding.progressBar3.visibility==View.GONE
                    showToast("Authentication failed." + task.exception)


                }


            }
    }
}


    }

