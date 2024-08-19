package com.example.assignment2mad.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    //There are three tables in the database which is student,comment and post
    entities = [Student::class, PostingFeeds::class, CommentingOnPostFeeds::class],
    version = 1,
    exportSchema = false
    )
//StudentsDBHandler class which extends to the RoomDatabase()
abstract class StudentsDBHandler: RoomDatabase(){
    //This can be used to access the dao using the interface StudentSlashUserDao
    //This is used to retrieve the data of the student table
    //this abstract method gives access to the Dao linked to the Student entity
    abstract val dao: StudentSlashUserDao


    //This can be used to access the postdao using the interface postDao
    //This is used to retrieve the data of the post table
    //this abstract method gives access to the Dao linked to the post entity
    abstract val postingFeedsDao: PostingFeedsDao


    //This can be used to access the commentdao using the interface commentDao
    //This is used to retrieve the data of the comment table
    //this abstract method gives access to the Dao linked to the comment entity
    abstract val commentingOnPostFeedsDao: CommentingOnPostFeedsDao


}