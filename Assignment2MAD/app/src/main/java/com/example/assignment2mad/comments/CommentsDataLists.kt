package com.example.assignment2mad.comments

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.assignment2mad.database.CommentingOnPostFeeds


data class CommentsDataLists(
    //display a list of comments data in the user home page and used to stores the lists of comment data
    val commentingOnFeeds: List<CommentingOnPostFeeds> = emptyList(),
    //     used to stores the commentKoId
    val commentKoId: MutableState<Int> = mutableIntStateOf(0),

    //     used to stores the students descContentsComment
    val descContentsComment: MutableState<String> = mutableStateOf(""),

    //     used to stores the students dateAddedWhenPosted
    val dateAddedWhenComment: MutableState<Long> = mutableStateOf(System.currentTimeMillis())
)