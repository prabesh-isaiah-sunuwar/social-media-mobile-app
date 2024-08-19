package com.example.assignment2mad.comments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.R


@Composable
fun StudentAddCommentInPost(
    //declarces composable function of adding new comment data.
    navController: NavController,
    onEventClickForComment: (CommentOnClickEventButton) -> Unit,
    commentState: CommentsDataLists

) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()

        ) {
//This "New Comment for the post" text is a title of Add comment page
            Text(text = "New Comment for the post",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            OutlinedTextField(
                value = commentState.descContentsComment.value,
                onValueChange = { commentState.descContentsComment.value = it},//"it" is used so that the
                // application user can put their desired textfield
                label = { Text((stringResource(id = R.string.CommentDesc))) },
                modifier = Modifier.fillMaxWidth()
            )



            Spacer(modifier = Modifier.height(16.dp))
            Button( onClick = {
                //This onEventClickForComment helps to creata a comment data
                onEventClickForComment(
                    CommentOnClickEventButton.SaveCommentData(
                        //commentKoId,descContentsComment ,dateAddedWhenComment is in
                        // function SavePostData where it is in used to store the list
                        // of comment ,commentKoId,descContentsComment ,dateAddedWhenComment
                        commentKoId = commentState.commentKoId.value,
                        descContentsComment = commentState.descContentsComment.value,
                        dateAddedWhenComment = commentState.dateAddedWhenComment.value
                    )
                )
                // This bring You back to the main home page after adding comment data
                navController.popBackStack()
            },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                // Button color
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.ButtonColor),
                )){

                Text(text = "Add my comment")
            }



        }
    }

}