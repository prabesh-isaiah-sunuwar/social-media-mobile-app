package com.example.assignment2mad.posts

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.assignment2mad.database.PostingFeeds


data class PostsDataLists(
    //display a list of postingFeeds data in the user home page and used to stores the lists of postingFeeds data
    val postingFeeds: List<PostingFeeds> = emptyList(),

        //     used to stores the postKoId
    val postKoId: MutableState<Int> = mutableIntStateOf(0),

    //     used to stores the students descContentsPost
    val descContentsPost: MutableState<String> = mutableStateOf(""),

    //     used to stores the students dateAddedWhenPosted
    val dateAddedWhenPosted: MutableState<Long> = mutableStateOf(System.currentTimeMillis())
)