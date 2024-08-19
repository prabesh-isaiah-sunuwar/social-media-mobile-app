package com.example.assignment2mad.students

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.comments.CommentsDataLists
import com.example.assignment2mad.posts.PostOnClickEventButton
import com.example.assignment2mad.posts.PostsDataLists
import com.example.assignment2mad.database.Student
import java.text.SimpleDateFormat
import java.util.Locale



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomePage(
    student: Student,
    postState: PostsDataLists,
    commentState: CommentsDataLists,
    navController: NavController,
    onEventClickForPost: (PostOnClickEventButton) -> Unit

) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {

                        Text(
                            //Title for this page is Student data which is the admin panel
                            text = "Hello ${student.fullname}",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                },

                actions = {
                    IconButton(onClick = {
                        // logs out to the login page when the lock icon is pressed
                        navController.navigate("Login") {
                            // we use popupto 0 avoids muiltiple back button clicks to not stack the screens behind
                            popUpTo(0)
                        }

                    }) {
                        Icon(
                            //icon button
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Logout",
                            tint = Color(0xff002a51)
                        )
                    }
                }

            )
            Divider(
                thickness = 2.dp, // Adjust thickness as needed
                color = Color.LightGray
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

            items(postState.postingFeeds.size) { index ->
                AttributesOfCardItemsForThePostByStudent(//this is for when the data is added, these things are needed
                    postState = postState,
                    commentState = commentState,
                    index = index,
                    onEventClickForPost = onEventClickForPost,
                    navController = navController,
                    student = student
                )
            }

        }


    }
}

//This function is the attributes of what is in the card. There is fields , edit and delete button for the student data to be manupilated
@Composable
fun AttributesOfCardItemsForThePostByStudent(
    student: Student,
    postState: PostsDataLists,
    commentState: CommentsDataLists,
    index: Int,
    onEventClickForPost: (PostOnClickEventButton) -> Unit,
    navController: NavController
) {
    //declaring formatForDate in a variable
    val formatForDate = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    val borderRadius = 20.dp // gave the Border radius value declaring the borderRadius in a variable
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
//                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${student.fullname} of ",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = " ${student.semail}",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                Column{
                    //Editing post
                    IconButton(
                        onClick = {
                            //Edit post here
                            //onEventClickForPost is used to iterate with the
                            onEventClickForPost(PostOnClickEventButton.UpdatePostFeedsData(postState.postingFeeds[index]))
                            //navigates to the editing page for editing the student data by looking at the id
                            navController.navigate("StudentEditUpdateThePost")

                        },
                        modifier = Modifier.size(25.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit post")
                    }

                    //Deleting post
                    IconButton(
                        onClick = {

                            //onEventClickForPost is used to iterate with the
                            //It communicates with the postviewModel to command to delete the post
                            onEventClickForPost(PostOnClickEventButton.DeletePostFeedsData(postState.postingFeeds[index]))

                        },
                        modifier = Modifier.size(25.dp)
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete post")
                    }

                }
            }

            // Caption of th post
            Text(
                text = " ${postState.postingFeeds[index].descContentsPost}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 1.dp)
            )

            // Actions Row meaing the like and Add Comment
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 1.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //Add comments
                IconButton(
                    onClick = {
                        commentState.descContentsComment.value = ""
                        //Navigates to the student add comment in post and popUpTo prevents multiple back taps
                        navController.navigate("StudentAddCommentInPost")

                    },
                    modifier = Modifier.size(34.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.MailOutline, contentDescription = "Comment")
                }
            }

            // We view that comments here
            Text(
                text = "View all the comments",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp).clickable {
                    navController.navigate("StudentViewTheCommentInPost")
                }
            )

            // Time posted
            Text(
                text =" ${formatForDate.format(postState.postingFeeds[index].dateAddedWhenPosted)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}