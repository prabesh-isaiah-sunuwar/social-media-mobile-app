package com.example.assignment2mad.posts

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
fun StudentEditUpdateThePost(
    //These are the declaration of a composable function is to update a Post data.
    navController: NavController,
    onEventClickForPost: (PostOnClickEventButton) -> Unit,
    postState: PostsDataLists

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
//This "Editing my post" text is a title of user editing their wanted post
            Text(text = "Editing my post",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp

            )
            OutlinedTextField(
                value = postState.descContentsPost.value,
                onValueChange = { postState.descContentsPost.value = it},//"it" is used so that the
                // application user can put their desired textField
                label = { Text((stringResource(id = R.string.CommentDesc))) },
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )






            Spacer(modifier = Modifier.height(16.dp))
            Button( onClick = {
                //This clickevent helps to creata a post data
                onEventClickForPost(
                    PostOnClickEventButton.SavePostData(
                        //schoolEmail,descContentsPost ,dateAddedWhenPosted is in
                        // function SavePostData where it is in used to store the list
                        // of post ,schoolEmail,descContentsPost ,dateAddedWhenPosted
                        postKoId = postState.postKoId.value,
                        descContentsPost = postState.descContentsPost.value,
                        dateAddedWhenPosted = postState.dateAddedWhenPosted.value
                    )
                )
                //This bring You back to the main user home page after editing the post
                navController.popBackStack()

            },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                // Button color
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.ButtonColor),
                )){

                Text(text = "Edit my post")
            }



        }
    }

}