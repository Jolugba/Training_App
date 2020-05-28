package com.tinuade.nougat_home_teach.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tinuade.nougat_home_teach.R
import com.tinuade.nougat_home_teach.databinding.ActivityLoginBinding
import com.tinuade.nougat_home_teach.model.UserType
import com.tinuade.nougat_home_teach.model.Users
import com.tinuade.nougat_home_teach.sharedpref.SharePref
import com.tinuade.nougat_home_teach.util.openActivity
import com.tinuade.nougat_home_teach.util.showLog
import com.tinuade.nougat_home_teach.util.showToast

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var users: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        //init firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        users = database.getReference("users")



        if (mAuth.currentUser != null) {
            if (SharePref.getINSTANCE(applicationContext).getStringData("userType") == UserType.TEACHER.name) {

                val intent = Intent(this, TeacherDashBoardActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                val intent = Intent(this, ParentDashBoardActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    fun goToRegister(view: View) {
        val intent = Intent(this, SignUpScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun login(view: View) {
        loginUser()
    }

    private fun loginUser() {
        val email = binding.loginEmailEditText.text.toString().trim()
        val password = binding.loginPasswordEditText.text.toString().trim()

        if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.loginEmailEditText.error = "Please Enter a valid Email Address"
        } else if (TextUtils.isEmpty(password)) {
            binding.loginPasswordEditText.error = "Please Enter a valid Password"
        } else {
            binding.progressBar.visibility==View.VISIBLE
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        mAuth.currentUser?.uid?.let { users.child(it) }
                            ?.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {

                                }

                                override fun onDataChange(p0: DataSnapshot) {
                                    val value = p0.getValue(Users::class.java)
                                    SharePref.getINSTANCE(applicationContext)
                                        .setStringData("userType", value?.userType?.name)
                                    if (value?.userType?.name == UserType.TEACHER.name) {
                                        binding.progressBar.visibility==View.GONE

                                        openActivity<TeacherDashBoardActivity> {
                                            showToast("Login Successful")
                                        }
                                        finish()
                                    } else {
                                        openActivity<ParentDashBoardActivity> {
                                            showToast("Login Successful")
                                        }
                                        finish()
                                    }
                                }

                            })


                    } else {
                        // If sign in fails, display a message to the user.
                       // showLog("signInWithEmail:failure" + task.exception)
                        binding.progressBar.visibility==View.GONE
                        showToast("Authentication failed.")


                    }


                }
        }


    }


    // Hide the keyboard.
    // val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //imm.hideSoftInputFromWindow(view.windowToken, 0)
}
