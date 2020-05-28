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
import com.tinuade.nougat_home_teach.databinding.ActivityParentSignUpBinding
import com.tinuade.nougat_home_teach.model.ParentUsers
import com.tinuade.nougat_home_teach.model.UserType
import com.tinuade.nougat_home_teach.util.showToast

class ParentSignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParentSignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var users: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parent__sign__up_)
        //init firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        users = database.getReference("users")




        binding.parentRegisterButton.setOnClickListener {
            signUP()
        }

    }

    fun goToLogin(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signUP() {
        val email = binding.parentEmailSignUp.text.toString().trim()
        val name = binding.parentNameSignUp.text.toString().trim()
        val password = binding.parentPasswordSignUp.text.toString().trim()


        if (TextUtils.isEmpty(name) && name.length < 5) {
            binding.parentNameSignUp.error = "enter a valid name"
            binding.parentNameSignUp.requestFocus()
        } else if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.parentEmailSignUp.error = "Please Enter a valid Email Address"
            binding.parentEmailSignUp.requestFocus()
        } else if (TextUtils.isEmpty(email) && !(email.contains("@"))) {
            binding.parentEmailSignUp.error = "Please Enter valid Email address"
            binding.parentEmailSignUp.requestFocus()
        } else if (TextUtils.isEmpty(password)) {
            binding.parentPasswordSignUp.error = "Please Enter a valid Password"
            binding.parentPasswordSignUp.requestFocus()
        } else if (password.isEmpty() || password.length < 8) {
            binding.parentPasswordSignUp.error = ("Your password must be minimum of 8 characters")
        } else {
            //register users
            binding.progressBar2.visibility==View.VISIBLE
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        //save user to the database
                        val parent = ParentUsers(name, email, password, UserType.PARENT)
                        mAuth.currentUser?.uid?.let { users.child(it) }
                            ?.setValue(parent)
                            ?.addOnCompleteListener {
                                binding.progressBar2.visibility==View.GONE
                                showToast("Authentication Successful")
                                val intent = Intent(this, ParentDashBoardActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                    } else {
                        binding.progressBar2.visibility==View.GONE
                        showToast("Authentication failed." + task.exception)


                    }


                }
        }
    }
}





