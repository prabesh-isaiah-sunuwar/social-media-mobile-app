package com.example.assignment2mad.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface StudentSlashUserDao {
    //This both updates or inserts the data saving dev some time
    @Upsert
    //Suspend aids the asynchronous operation.
    suspend fun upsertStudentData(student: Student)
//Deletes student data
    @Delete
    suspend fun deleteStudentData(student: Student)
//Fetches the students email and password
    @Query("SELECT * FROM students WHERE semail = :semail AND password = :password")
    fun getStudentsSemailAndPassword(semail: String, password: String): Flow<Student?>

    //Fetches the students id
    @Query("SELECT * FROM students WHERE stuStudentId = :stuStudentId")
    fun getStudentId(stuStudentId: Int): Flow<Student?>


    //This means that the data is shown according to the oldest student data at the top to the newest student data at the bottom.
    @Query("SELECT * FROM students ORDER BY addeddate")
    fun getStudentDataOrderedByDateAdded(): Flow<List<Student>>

    //This shows you the student data from A at the top to Z at the bottom of the screen
    @Query("SELECT * FROM students ORDER BY semail ASC")
    fun getStudentsDataOrderedBySchoolEmailAlphabetically(): Flow<List<Student>>


}