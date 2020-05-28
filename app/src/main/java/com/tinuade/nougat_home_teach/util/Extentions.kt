package com.tinuade.nougat_home_teach.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.tinuade.nougat_home_teach.BuildConfig
import com.tinuade.nougat_home_teach.model.TeacherUsers

// Extensions for Log class
fun Activity.showLog(message: ArrayList<TeacherUsers>) {
    if (BuildConfig.DEBUG) Log.i(this::class.java.simpleName, message.toString())
}

//Extension for Toast Class
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
inline fun <reified T :Activity> Context.openActivity(block:Intent.()->Unit={}){
    val intent=Intent(this,T::class.java)
    block(intent)
    startActivity(intent)

}

