package com.example.assignment2mad.posts

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2mad.database.PostingFeeds
import com.example.assignment2mad.database.PostingFeedsDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PostView(
    private val postingFeedsDao: PostingFeedsDao

): ViewModel() {

    //tell us whether the variable that tells us whether our post data is sorted according to the  date as being added
    //want the post data to be the ordered by as they are added not by the id until the students changes it
    private val sortedOutByThePostDateAdded = MutableStateFlow(true)
    //defining a variable currentStudentDataEdit which is to stores the latest updated student data
  private var currentPostDataEdit: PostingFeeds? = null



    @OptIn(ExperimentalCoroutinesApi::class)
    private var postingFeeds =
    //This fines a way to get the post from the database which then
        // sorts based on the type of the data and the getPostDataOrderedByDateAdded
        sortedOutByThePostDateAdded.flatMapLatest { sort->
            if(sort){
                postingFeedsDao.getPostDataOrderedByDateAdded()
            }else{
                postingFeedsDao.getPostDataOrderedBySchoolEmailAlphabetically()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    //this MutableStateFlow(PostLists())is used to track the PostLists()
    val _stateOfThePostData = MutableStateFlow(PostsDataLists())
    val postState =

        combine(_stateOfThePostData, sortedOutByThePostDateAdded, postingFeeds) { postState, sortedOutByThePostDateAdded, postingFeeds ->
            postState.copy(
                //the post data is the same as the new posts data
                postingFeeds = postingFeeds


            )

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), PostsDataLists())



    fun onTheEventButtonForPost(event: PostOnClickEventButton){
        when (event){

            is PostOnClickEventButton.DeletePostFeedsData -> {
                viewModelScope.launch{
                    //when the delete icon button is pressed, it calls the postdao to delete the data
                    //As deletePost() was a suspend function, we launch the viewModelScope
                    postingFeedsDao.deletePostFeeds(event.postingFeeds)
                }
            }

//The savePostData aids in the creating and updating and then it clears the input fields once the share post button or the update post button is clicked.
            //The latest updated data will occur if it is a new post then a new post data will be created.
            is PostOnClickEventButton.SavePostData -> {
                val postingFeeds = currentPostDataEdit?.copy(
                    postKoId = postState.value.postKoId.value,
                    descContentsPost = postState.value.descContentsPost.value,//Saves the post data upon the addition or editing
                    dateAddedWhenPosted = System.currentTimeMillis()
                ) ?: PostingFeeds(
                    postKoId = postState.value.postKoId.value,
                    descContentsPost = postState.value.descContentsPost.value,//Saves the post data upon the addition or editing
                    dateAddedWhenPosted = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    //this is lauched to add or edit the post data
                    postingFeedsDao.upsertPostFeeds(postingFeeds)
                }

                _stateOfThePostData.update {
                    it.copy(
                        //sets descContentsPost field to be an empty as we do not want the descContentsPost field to be occupied
                        // with any value as it should already be added in the room database
                        postKoId = mutableIntStateOf(0),
                        descContentsPost = mutableStateOf(""),
                        dateAddedWhenPosted = mutableStateOf(System.currentTimeMillis())

                    )
                }
                currentPostDataEdit = null

            }
            //.UpdatePostData is used to edit the current post data into a new post data by looking at the ID
            is PostOnClickEventButton.UpdatePostFeedsData -> {
                currentPostDataEdit = event.postingFeeds
                _stateOfThePostData.update {
                    it.copy(
                        //Successfully saves the post data after editing the data
                        postKoId = mutableIntStateOf(event.postingFeeds.postKoId),
                        descContentsPost = mutableStateOf(event.postingFeeds.descContentsPost),
                        dateAddedWhenPosted = mutableStateOf(System.currentTimeMillis())
                    )
                }
            }


            PostOnClickEventButton.SortPostData -> {
                sortedOutByThePostDateAdded.value = !sortedOutByThePostDateAdded.value

            }
        }

    }



}