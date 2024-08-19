package com.example.assignment2mad.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


//the data class which is "Student" is declared
@Entity(tableName = "students")

data class Student(
    //each row itself has their own attribute
    @ColumnInfo(name = "fullname")
    val fullname: String,

    @ColumnInfo(name = "dateofbirth")
    val dateofbirth: String,

    @ColumnInfo(name = "semail")
    val semail: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "addeddate")
    val addeddate: Long,

    @ColumnInfo(name = "updateddate")
    val updateddate: Long? = null,

    @PrimaryKey(autoGenerate = true)
    //primary key is suppose to be unique which is the id itself
    @ColumnInfo(name = "stuStudentId")
    val stuStudentId: Int = 0
    //it give the room database an idea that 0 is the primary key
)
