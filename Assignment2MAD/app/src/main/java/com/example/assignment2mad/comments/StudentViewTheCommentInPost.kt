package com.example.assignment2mad.comments

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.database.Student
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentViewTheCommentInPost(
    student: Student,
    commentState: CommentsDataLists,
    navController: NavController,
    onEventClickForComment: (CommentOnClickEventButton) -> Unit

) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {

                    Text(
                        //Title for this page
                        text = "Comments",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                },

                actions = {
                    IconButton(onClick = {
                        // logs out to the login page when the lock icon is pressed
                        navController.navigate("UserHomePage") {
                            // we use popupto 0 avoids muiltiple back button clicks to not stack the screens behind
                            popUpTo(0)
                        }

                    }) {
                        Icon(
                            //icon button
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Go Back to main page",
                            tint = Color(0xff002a51)
                        )
                    }
                }

            )


        }


    ) { paddingValues ->

        LazyColumn(
            //created inside a scaffold
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(13.dp),
            verticalArrangement = Arrangement.spacedBy(17.dp)
        ) {

            items(commentState.commentingOnFeeds.size) { index ->
                AttributesOfCardItemsForTheCommentsPostedByStudent(//this is for when the data is added, these things are needed
                    commentState = commentState,
                    index = index,
                    student = student,
                    onEventClickForComment = onEventClickForComment
                )
            }

        }


    }
}

//This function is the attributes of what is in the card. There is fields , edit and delete button for the student data to be manupilated
@Composable
fun AttributesOfCardItemsForTheCommentsPostedByStudent(
    student: Student,
    commentState: CommentsDataLists,
    index: Int,
    onEventClickForComment: (CommentOnClickEventButton) -> Unit

    ) {
    //declaring formatForDate in a variable
    val formatForDate = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    val borderRadius = 20.dp // gave the Border radius value declaring the borderRadius in a variable
    // Getting the commentKoId from database
    val commentKoId = commentState.commentingOnFeeds[index].commentKoId
    // Getting this from the object ManagelikeComment
    var likeToASpecificComment by remember { mutableStateOf(
        ManagelikeComment.commentIsLiked(commentKoId))
    }
    //context for the toast
    val context = LocalContext.current.applicationContext


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(borderRadius)) // Add black border as well as
            .clip(RoundedCornerShape(borderRadius))// Setting the border radius for the box
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(11.dp) // Padding inside the border
        ) {
            // Header with name and email
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "From: ${student.fullname}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        //onEventClickForComment is used to iterate with the
                        //It communicates with the commentviewModel to command to delete the comment
                        onEventClickForComment(CommentOnClickEventButton.DeleteComments(commentState.commentingOnFeeds[index]))
                        Toast.makeText(context, "Comment is deleted", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(10.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.Clear, contentDescription = "delete")
                }
            }


            // Actions Row which has Comment that is written and the like button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 1.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Comment that is written

                Text(
                    text = " ${commentState.commentingOnFeeds[index].descContentsComment}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 1.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
//Like button for the comment
                Icon(
                    imageVector = if (likeToASpecificComment) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (likeToASpecificComment) "Liked" else "Disliked",
                    tint = if (likeToASpecificComment) Color.Red else Color.DarkGray,
                    modifier = Modifier.clickable {
                        // Toggle like state and update global LikeManager
                        ManagelikeComment.pressingTheLikeButton(commentKoId)
                        likeToASpecificComment = !likeToASpecificComment
                    }
                )

            }

            // Time posted after commenting
            Text(
                text =" ${formatForDate.format(commentState.commentingOnFeeds[index].dateAddedWhenComment)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}



//The object who manages the likes depending on the comment id is here
object ManagelikeComment {
    // Using tht mutableSetOf<Int> to track liked comments via comment id
    private val likedTheSpecificCommentsViaTheirId = mutableSetOf<Int>()

    fun pressingTheLikeButton(commentKoId: Int) {
        if (likedTheSpecificCommentsViaTheirId.contains(commentKoId)) {
            likedTheSpecificCommentsViaTheirId.remove(commentKoId)
        } else {
            likedTheSpecificCommentsViaTheirId.add(commentKoId)
        }
    }

    fun commentIsLiked(commentKoId: Int): Boolean {
        return likedTheSpecificCommentsViaTheirId.contains(commentKoId)
    }
}
