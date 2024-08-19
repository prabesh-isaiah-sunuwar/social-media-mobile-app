package com.example.assignment2mad.comments

import com.example.assignment2mad.database.CommentingOnPostFeeds
import com.example.assignment2mad.database.Student
import com.example.assignment2mad.students.StudentOnClickEventButton


//This page file is used when a comment button is clicked.
//interface declared
sealed interface CommentOnClickEventButton {
    // user use to sort out the data of Comment
    object SortPostData: CommentOnClickEventButton

    // Admin use this operation to delete the student data
    data class DeleteComments(val commentingOnPostFeeds: CommentingOnPostFeeds): CommentOnClickEventButton
    //This property is for saving the Comment data
    data class SaveCommentData(

        val commentKoId: Int,
        val descContentsComment: String,
        val dateAddedWhenComment: Long


    ): CommentOnClickEventButton
    // this is for when the add comment button is clicked then, the data will be saved to the db



}