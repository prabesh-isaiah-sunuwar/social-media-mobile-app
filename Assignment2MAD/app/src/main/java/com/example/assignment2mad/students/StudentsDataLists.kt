package com.example.assignment2mad.students

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.assignment2mad.database.Student


data class StudentsDataLists(
    //display a list of student data in the admin page and used to stores the lists of student data
    val students: List<Student> = emptyList(),
    // used to stores the students fullname
    val fullname: MutableState<String> = mutableStateOf(""),
    //     used to stores the students dob
    val dateofbirth: MutableState<String> = mutableStateOf(""),

    //     used to stores the students semail meaning school email
    val semail: MutableState<String> = mutableStateOf(""),

    //     used to stores the students password
    val password: MutableState<String> = mutableStateOf(""),

    //     used to stores the students addeddate
    val addeddate: MutableState<Long> = mutableStateOf(System.currentTimeMillis()),

    //     used to stores the students addeddate
    val updateddate: MutableState<Long> = mutableStateOf(System.currentTimeMillis()),



)