package com.example.assignment2mad.comments



import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2mad.database.CommentingOnPostFeeds
import com.example.assignment2mad.database.CommentingOnPostFeedsDao
import com.example.assignment2mad.posts.PostOnClickEventButton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CommentView(
    private val commentingOnPostFeedsDao: CommentingOnPostFeedsDao

): ViewModel() {

    //tell us whether the variable that tells us whether our post data is sorted according to the  date as being added
    //want the post data to be the ordered by as they are added not by the id until the students changes it
    private val sortedOutByTheCommentDateAdded = MutableStateFlow(true)
    //defining a variable currentStudentDataEdit which is to stores the latest updated student data
    private var currentCommentDataEdit: CommentingOnPostFeeds? = null



    @OptIn(ExperimentalCoroutinesApi::class)
    private var commentingOnPostFeeds =
    //This fines a way to get the post from the database which then
        // sorts based on the type of the data and the getPostDataOrderedByDateAdded
        sortedOutByTheCommentDateAdded.flatMapLatest { sort->
            if(sort){
                commentingOnPostFeedsDao.getStudentCommentDataOrderedByCommentDateAdded()
            }else{
                commentingOnPostFeedsDao.getCommentDataOrderedByCommentKoId()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    //this MutableStateFlow(PostLists())is used to track the PostLists()
    val _stateOfTheCommentData = MutableStateFlow(CommentsDataLists())
    val commentState =

        combine(_stateOfTheCommentData, sortedOutByTheCommentDateAdded, commentingOnPostFeeds) { commentState, sortedOutByTheCommentDateAdded, commentingOnFeeds ->
            commentState.copy(
                //the post data is the same as the new posts data
                commentingOnFeeds = commentingOnFeeds


            )

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), CommentsDataLists())



    fun onTheEventButtonForComment(event: CommentOnClickEventButton){
        when (event){

            is CommentOnClickEventButton.DeleteComments -> {
                viewModelScope.launch{
                    //when the delete icon button is pressed, it calls the postdao to delete the data
                    //As deletePost() was a suspend function, we launch the viewModelScope
                    commentingOnPostFeedsDao.deleteComments(event.commentingOnPostFeeds)
                }
            }


//The savePostData aids in the creating and updating and then it clears the input fields once the share post button or the update post button is clicked.
            //The latest updated data will occur if it is a new post then a new post data will be created.
            is CommentOnClickEventButton.SaveCommentData -> {
                val commentingOnFeeds = currentCommentDataEdit?.copy(
                    commentKoId = commentState.value.commentKoId.value,
                    descContentsComment = commentState.value.descContentsComment.value,//Saves the post data upon the addition or editing
                    dateAddedWhenComment = System.currentTimeMillis()
                ) ?: CommentingOnPostFeeds(
                    commentKoId = commentState.value.commentKoId.value,
                    descContentsComment = commentState.value.descContentsComment.value,//Saves the post data upon the addition or editing
                    dateAddedWhenComment = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    //this is lauched to add or edit the post data
                    commentingOnPostFeedsDao.insertingComment(commentingOnFeeds)
                }

                _stateOfTheCommentData.update {
                    it.copy(
                        //sets descContentsPost field to be an empty as we do not want the descContentsPost field to be occupied
                        // with any value as it should already be added in the room database
                        commentKoId = mutableIntStateOf(0),
                        descContentsComment = mutableStateOf(""),
                        dateAddedWhenComment = mutableStateOf(System.currentTimeMillis())

                    )
                }
                currentCommentDataEdit = null

            }



            CommentOnClickEventButton.SortPostData -> {
                sortedOutByTheCommentDateAdded.value = !sortedOutByTheCommentDateAdded.value

            }
        }

    }



}