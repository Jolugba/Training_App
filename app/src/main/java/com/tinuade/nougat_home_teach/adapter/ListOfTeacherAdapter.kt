package com.tinuade.nougat_home_teach.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinuade.nougat_home_teach.R
import com.tinuade.nougat_home_teach.model.TeacherUsers
import com.tinuade.nougat_home_teach.util.showToast
import kotlinx.android.synthetic.main.list_item.view.*

class ListOfTeacherAdapter(val context: Context, private val teacherUsers:  List<TeacherUsers>): RecyclerView.Adapter<ListOfTeacherAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView:android.view.View): RecyclerView.ViewHolder(itemView){


        var currentTeacher:TeacherUsers?=null
        var currentpostion:Int=0
        init {
            itemView.setOnClickListener{
                context.showToast(currentTeacher!!.fullName+ " is Clicked")

            }

        }
        fun setData(teacherList: TeacherUsers, position: Int) {
            itemView.teacherName.text=teacherList.fullName
            itemView.teacherSubject.text=teacherList.subject
            itemView.teacherEmail.text=teacherList.fullName
            this.currentTeacher=teacherList
            this.currentpostion=position

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
      return teacherUsers.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val teacherList=teacherUsers[position]
        holder.setData(teacherList,position)
    }
}