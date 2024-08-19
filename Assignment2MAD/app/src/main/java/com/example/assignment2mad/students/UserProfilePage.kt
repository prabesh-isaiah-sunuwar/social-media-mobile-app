package com.example.assignment2mad.students


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.R
import com.example.assignment2mad.database.Student


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfilePage(
    state: StudentsDataLists,
    navController: NavController,
    onEventClick: (StudentOnClickEventButton) -> Unit,
    student: Student

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        //Title for this page is Student data which is the admin panel
                        text = "Profile",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            )
        },
        ) { paddingValues ->
        Column(
            modifier = Modifier
                //created inside a scaffold
                .padding(paddingValues)
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {


                UserProfile(//this is for when the data is added, these things are needed
                    index = state.students.indexOf(student),
                    onEventClick = onEventClick,
                    state = state,
                    student = student,
                    navController = navController,
                )

            Spacer(modifier = Modifier.height(17.dp))
            Button(
                onClick = {
                    //Logs out to the Login page
                    navController.navigate("Login") {
                        //popUpTo prevents multiple back buttons clicks
                        popUpTo(0)
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .height(45.dp),
                //Button color
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF4278E2),
                )

            ) {
                //Button for the user meaning the students login
                Text("Logout")
            }


        }
            }








    }



//This function is the attributes of what is in the card. There is fields , edit and delete button for the student data to be manupilated
@Composable
fun UserProfile(
    state: StudentsDataLists,
    index: Int,
    onEventClick: (StudentOnClickEventButton) -> Unit,
    navController: NavController,
    student: Student
) {
    Card {
        Modifier.padding(20.dp)

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFffecf2))


        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp)


            ) {
                Text(
                    //This is the data for the fullname of a student
                    text = "Full name: ${student.fullname}",
                    fontSize = 12.sp,

                    )

                Spacer(modifier = Modifier.height(5.dp))

                Text(//This is the data for the DOB of a student
                    text = "Date of birth: ${student.dateofbirth}",
                    fontSize = 12.sp,


                    )

                Spacer(modifier = Modifier.height(5.dp))

                Text(//This is the data for the SCHOOL EMAIL of a student
                    text = "School email: ${student.semail}",
                    fontSize = 12.sp,


                    )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    //This is the data for the password of a student
                    text = "Password: ${student.password}",
                    fontSize = 12.sp,
                )


            }
            //Edit button
            IconButton(
                onClick = {
                    //onEventClick is used to iterate with the
                    onEventClick(StudentOnClickEventButton.UpdateStudentData(state.students[index]))
                    //navigates to the editing page for editing the student data by looking at the id
                    navController.navigate("StudentEditProfile")
                }
            ) {

                Icon(
                    //edit icon
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit my Profile",
                    modifier = Modifier.size(29.dp),
                    tint = colorResource(id = R.color.ButtonColor)//button color
                )

            }

        }

    }
}