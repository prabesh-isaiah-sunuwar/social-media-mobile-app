package com.example.assignment2mad.students

import com.example.assignment2mad.database.Student


//This page file is used when a button is clicked.
//interface declared
sealed interface StudentOnClickEventButton {
    // user use to sort out the data of students
    object SortStudentData: StudentOnClickEventButton

    // Admin use this operation to delete the student data
    data class DeleteStudentData(val student: Student): StudentOnClickEventButton


    // Admin use this operation to edit the student data
    data class UpdateStudentData(val student: Student): StudentOnClickEventButton

    //This property is for saving the student data
    data class SaveStudentData(
        val fullname: String,
        val dateofbirth: String,
        val semail: String,
        val password: String,
        val addeddate: Long,
        val updateddate: Long


    ): StudentOnClickEventButton
    // this is for when the add button is clicked then, the data will be saved to the db



}