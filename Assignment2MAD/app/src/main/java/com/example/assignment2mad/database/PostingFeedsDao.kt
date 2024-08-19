package com.example.assignment2mad.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface PostingFeedsDao {

    @Upsert
    suspend fun upsertPostFeeds(postingFeeds: PostingFeeds)

    //Deletes post data
    @Delete
    suspend fun deletePostFeeds(postingFeeds: PostingFeeds)



    //This means that the data is shown according to the oldest student data at the top to the newest student data at the bottom.
    @Query("SELECT * FROM postingFeeds ORDER BY dateAddedWhenPosted")
    fun getPostDataOrderedByDateAdded(): Flow<List<PostingFeeds>>

    //This shows you the post data from A at the top to Z at the bottom of the screen
    @Query("SELECT * FROM postingFeeds ORDER BY postKoId ASC")
    fun getPostDataOrderedBySchoolEmailAlphabetically(): Flow<List<PostingFeeds>>








}
