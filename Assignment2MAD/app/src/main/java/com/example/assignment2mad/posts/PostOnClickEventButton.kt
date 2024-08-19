package com.example.assignment2mad.posts

import com.example.assignment2mad.database.PostingFeeds


//This page file is used when a button is clicked.
//interface declared
sealed interface PostOnClickEventButton {
    // user use to sort out the data of post
    object SortPostData: PostOnClickEventButton

    // Student use this operation to delete the post data
    data class DeletePostFeedsData(val postingFeeds: PostingFeeds): PostOnClickEventButton

    // Student use this operation to edit the post data
    data class UpdatePostFeedsData(val postingFeeds: PostingFeeds): PostOnClickEventButton

    //This property is for saving the post data
    data class SavePostData(
        val postKoId: Int,
        val descContentsPost: String,
        val dateAddedWhenPosted: Long


    ): PostOnClickEventButton
    // this is for when the add button is clicked then, the data will be saved to the db



}