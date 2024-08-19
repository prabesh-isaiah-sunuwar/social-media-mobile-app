package com.example.assignment2mad.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "postingFeeds")
//the data class which is "PostingFeeds" is declared
data class PostingFeeds(

    @ColumnInfo(name = "descContentsPost")
    val descContentsPost: String,
    @ColumnInfo(name = "dateAddedWhenPosted")
    val dateAddedWhenPosted: Long,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "postKoId")
    //primary key is suppose to be unique which is the id itself
    val postKoId: Int = 0
    //it give the room database an idea that 0 is the primary key

)








