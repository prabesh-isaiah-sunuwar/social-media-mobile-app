package com.example.assignment2mad.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "commentingOnPostFeeds")

//the data class which is "CommentingOnPostFeeds" must be declared here

data class CommentingOnPostFeeds(


    @ColumnInfo(name = "descContentsComment")
    val descContentsComment: String,
    @ColumnInfo(name = "dateAddedWhenComment")
    val dateAddedWhenComment: Long,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "commentKoId")
    //primary key is suppose to be unique which is the id itself in this case
    val commentKoId: Int = 0
    //room database will have an idea that 0 means that it is a primary key
)













