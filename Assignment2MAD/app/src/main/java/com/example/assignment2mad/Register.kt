package com.example.assignment2mad

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.students.StudentOnClickEventButton
import com.example.assignment2mad.students.StudentsDataLists


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    navController: NavController,
    onEventClick: (StudentOnClickEventButton) -> Unit,
    state: StudentsDataLists

) {
    //This context is for toasting
    val context = LocalContext.current.applicationContext
    // this is for the background color to make it gradient color
    val gradientColors = Brush.linearGradient(
        colors = listOf(colorResource(id = R.color.Gradient1), colorResource(id = R.color.Gradient2),colorResource(id = R.color.Gradient3))
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientColors)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        TopAppBar(
            //Title of the page is Register which means the registration for the students
            title = { Text(text = "Register", fontSize = 29.sp, fontWeight = FontWeight.SemiBold) }
        )

        OutlinedTextField(
            value = state.fullname.value,
            onValueChange = { state.fullname.value = it},//"it" is used so that the application user can put their desired textfield
            label = { Text((stringResource(id = R.string.fullName))) },
            colors = TextFieldDefaults.colors(
                //When the text field is clicked the icon color will change to DarkGrayish
                focusedLeadingIconColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the icon color will change to Gray
                unfocusedLeadingIconColor = Color.Gray,
                //When the text field is clicked the label color will change to DarkGrayish which is inside the rectangle
                focusedLabelColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the label color will change to Gray which is inside the rectangle
                unfocusedLabelColor = Color.Gray,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                focusedContainerColor = Color.White,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = colorResource(id = R.color.greyishBlack),
                unfocusedIndicatorColor = Color.LightGray

            ), leadingIcon = {  //icon
                Icon(imageVector = Icons.Default.Person, contentDescription = "Fullname")
            }

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value =  state.dateofbirth.value,
            onValueChange = { state.dateofbirth.value = it},//"it" is used so that the application user can put their desired textfield
            label = { Text((stringResource(id = R.string.Dob))) },
            colors = TextFieldDefaults.colors(
                //When the text field is clicked the icon color will change to DarkGrayish
                focusedLeadingIconColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the icon color will change to Gray
                unfocusedLeadingIconColor = Color.Gray,
                //When the text field is clicked the label color will change to DarkGrayish which is inside the rectangle
                focusedLabelColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the label color will change to Gray which is inside the rectangle
                unfocusedLabelColor = Color.Gray,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                focusedContainerColor = Color.White,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = colorResource(id = R.color.greyishBlack),
                unfocusedIndicatorColor = Color.LightGray

            ), leadingIcon = {  //icon
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "DOB")
            }

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = state.semail.value,
            onValueChange = { state.semail.value = it},//"it" is used so that the application user can put their desired textfield
            label = { Text((stringResource(id = R.string.sEmail))) },
            colors = TextFieldDefaults.colors(
                //When the text field is clicked the icon color will change to DarkGrayish
                focusedLeadingIconColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the icon color will change to Gray
                unfocusedLeadingIconColor = Color.Gray,
                //When the text field is clicked the label color will change to DarkGrayish which is inside the rectangle
                focusedLabelColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the label color will change to Gray which is inside the rectangle
                unfocusedLabelColor = Color.Gray,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                focusedContainerColor = Color.White,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = colorResource(id = R.color.greyishBlack),
                unfocusedIndicatorColor = Color.LightGray

            ), leadingIcon = {  //icon
                Icon(imageVector = Icons.Default.Email, contentDescription = "School Email")
            }

        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = state.password.value,
            onValueChange = { state.password.value = it},//"it" is used so that the application user can put their desired textfield
            label = { Text((stringResource(id = R.string.studentPassword))) },
            visualTransformation = PasswordVisualTransformation(),//this line is it makes the plain text password in dots
            colors = TextFieldDefaults.colors(
                //When the text field is clicked the icon color will change to DarkGrayish
                focusedLeadingIconColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the icon color will change to Gray
                unfocusedLeadingIconColor = Color.Gray,
                //When the text field is clicked the label color will change to DarkGrayish which is inside the rectangle
                focusedLabelColor = colorResource(id = R.color.greyishBlack),
                //When the text field is unclicked the label color will change to Gray which is inside the rectangle
                unfocusedLabelColor = Color.Gray,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                focusedContainerColor = Color.White,
                //When the text field is clicked the container color will change to white which is the perimeter of the rectangle
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = colorResource(id = R.color.greyishBlack),
                unfocusedIndicatorColor = Color.LightGray

            ), leadingIcon = {
                //icon
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                //This clickevent helps to creata a student data
                onEventClick(
                    StudentOnClickEventButton.SaveStudentData(
                    //fullname, dateofbirth ,semail and password is in function savestudentdata where it is in used to store the list of students ,fullname,dateofbirth ,password and semail
                    fullname = state.fullname.value,
                    dateofbirth = state.dateofbirth.value,
                    semail = state.semail.value,
                    password = state.password.value,
                    addeddate = state.addeddate.value,
                    updateddate = state.updateddate.value
                ))
                //This bring You back to the main home page after adding the data of a student
                navController.popBackStack()
                Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_SHORT).show()
            },

            modifier = Modifier.fillMaxWidth().height(45.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.ButtonColor),//button color
            )

        ) {
            Text("Register")
        }


        Spacer(modifier = Modifier.height(50.dp))
        Button(
            //This navigates back to the login page
            onClick = { navController.navigate("Login") },
            modifier = Modifier.fillMaxWidth().height(45.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.ButtonColor),//button color
            )
        ) {
            Text("Go Back to Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Northampton Secondary School", fontSize = 15.sp , fontWeight = FontWeight.Bold, color = colorResource(id = R.color.Northampton))
    }

}