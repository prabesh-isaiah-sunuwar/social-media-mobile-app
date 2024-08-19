package com.example.assignment2mad.admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.R
import com.example.assignment2mad.students.StudentOnClickEventButton
import com.example.assignment2mad.students.StudentsDataLists
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPage(
    state: StudentsDataLists,
    navController: NavController,
    onEventClick: (StudentOnClickEventButton) -> Unit
) {
//context is initialised.
    //This context is for toasting
    val context = LocalContext.current.applicationContext


    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        //Title for this page is Student data which is the admin panel
                        text = "Student Data",
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
                        Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
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
        },
        floatingActionButton = {
            Box {
                //This floating action button role is to navigate to the add student data page to add the student data
                FloatingActionButton(onClick = {
                    //we need these fields for the button to work
                    state.fullname.value = ""
                    state.dateofbirth.value = ""
                    state.semail.value = ""
                    state.password.value = ""
                    //Toast will appear after a successful addition
                    Toast.makeText(context, "Adding student data", Toast.LENGTH_SHORT).show()
                    navController.navigate("AddStudentData")
                }) {
                    Icon(
                        //Icon button
                        Icons.Default.Add,
                        contentDescription = "Add students",
                        tint = Color(0xff002a51)
                    )
                }
            }
        }

    ) { paddingValues ->

        LazyColumn(
            //created inside a scaffold
            contentPadding = paddingValues,
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(13.dp),
            verticalArrangement = Arrangement.spacedBy(19.dp)
        ) {

            items(state.students.size) { index ->
                AttributesOfCardItems(//this is for when the data is added, these things are needed
                    state = state,
                    index = index,
                    onEventClick = onEventClick,
                    navController = navController
                )
            }

        }


    }
}

//This function is the attributes of what is in the card. There is fields , edit and delete button for the student data to be manupilated
@Composable
fun AttributesOfCardItems(
    state: StudentsDataLists,
    index: Int,
    onEventClick: (StudentOnClickEventButton) -> Unit,
    navController: NavController
) {
    val formatForDate = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    Card {
        androidx.compose.ui.Modifier.padding(20.dp)

        Row(
            modifier = androidx.compose.ui.Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFffecf2))


        ) {
            Column(
                modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp)


            ) {
                Text(
                //This is the data for the fullname of a student
                    text = "Full name: ${state.students[index].fullname}",
                    fontSize = 12.sp,

                    )

                Spacer(modifier = androidx.compose.ui.Modifier.height(5.dp))

                Text(//This is the data for the DOB of a student
                    text = "Date of birth: ${state.students[index].dateofbirth}",
                    fontSize = 12.sp,


                    )

                Spacer(modifier = androidx.compose.ui.Modifier.height(5.dp))

                Text(//This is the data for the SCHOOL EMAIL of a student
                    text = "School email: ${state.students[index].semail}",
                    fontSize = 12.sp,


                    )
                Spacer(modifier = androidx.compose.ui.Modifier.height(5.dp))

                Text(
                    //This is the data for the password of a student
                    text = "Password: ${state.students[index].password}",
                    fontSize = 12.sp,
                )

                Text(
                    //This is the data for the date added of a student
                    text = "Date created: ${formatForDate.format(state.students[index].addeddate)}",
                    fontSize = 12.sp,
                )
                state.students[index].updateddate?.let {
                    Text(

                        text = "Date updated: ${formatForDate.format(it)}",
                        fontSize = 12.sp,
                    )

                }


            }
            //Edit button
            IconButton(
                onClick = {
                    //onEventClick is used to iterate with the
                    onEventClick(StudentOnClickEventButton.UpdateStudentData(state.students[index]))
                    //navigates to the editing page for editing the student data by looking at the id
                    navController.navigate("UpdateStudentData")
                }
            ) {

                Icon(
                    //edit icon
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Student data",
                    modifier = androidx.compose.ui.Modifier.size(29.dp),
                    colorResource(id = R.color.ButtonColor)//button color
                )

            }
            //Delete button
            IconButton(
                onClick = {
                    //when clicking a button,onEventClick is called to delete the data
                    onEventClick(StudentOnClickEventButton.DeleteStudentData(state.students[index]))
                }
            ) {

                Icon(
                    //delete icon  which is a button
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Deletes the Student data",
                    modifier = androidx.compose.ui.Modifier.size(29.dp),
                    tint = colorResource(id = R.color.ButtonColor)
                )

            }
        }

    }
}

