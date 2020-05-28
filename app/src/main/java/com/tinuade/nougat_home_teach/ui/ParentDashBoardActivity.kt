package com.tinuade.nougat_home_teach.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.tinuade.nougat_home_teach.R
import com.tinuade.nougat_home_teach.model.TeacherUsers
import com.tinuade.nougat_home_teach.model.Users
import com.tinuade.nougat_home_teach.util.showLog

class ParentDashBoardActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var users: DatabaseReference
    private var TAG = "ParentDashBoardActivity"
    private lateinit var recyclerView: RecyclerView
    lateinit var teacherList: ArrayList<TeacherUsers>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_dash_board)

        //init firebase
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        users = database.getReference("users")


        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager


        users.orderByChild("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val teacherUsers: Users? = dataSnapshot.getValue(Users::class.java)
                    teacherList.add(teacherUsers as TeacherUsers).toString()
                }
                showLog(teacherList)

//                val adapter = ListOfTeacherAdapter(this@ParentDashBoardActivity, teacherList)
//                recyclerView.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })



    }


}
