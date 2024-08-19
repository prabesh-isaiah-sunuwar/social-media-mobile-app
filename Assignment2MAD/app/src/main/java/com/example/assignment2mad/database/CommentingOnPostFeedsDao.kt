package com.example.assignment2mad.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface CommentingOnPostFeedsDao {
    @Insert
    suspend fun insertingComment(commentingOnPostFeeds: CommentingOnPostFeeds)

    //Deletes the comment data
    @Delete
    suspend fun deleteComments(commentingOnPostFeeds: CommentingOnPostFeeds)


    //Fetches all the data from the Comments table
    @Query("SELECT * FROM commentingOnPostFeeds")
    suspend fun getAllTheComments(): List<CommentingOnPostFeeds>


    //Fetches all the data by identifying the comment id from the Comments table
    @Query("SELECT * FROM commentingOnPostFeeds WHERE commentKoId = :commentKoId AND descContentsComment = :descContentsComment")
    fun gettingCommentsByThePostId(commentKoId: Int, descContentsComment: String): Flow<CommentingOnPostFeeds>



    @Query("SELECT * FROM commentingOnPostFeeds ORDER BY dateAddedWhenComment")
    fun getStudentCommentDataOrderedByCommentDateAdded(): Flow<List<CommentingOnPostFeeds>>


    //This shows you the comment data from 1 at the top to the end at the bottom of the screen
    @Query("SELECT * FROM commentingOnPostFeeds ORDER BY commentKoId ASC")
    fun getCommentDataOrderedByCommentKoId(): Flow<List<CommentingOnPostFeeds>>



}
