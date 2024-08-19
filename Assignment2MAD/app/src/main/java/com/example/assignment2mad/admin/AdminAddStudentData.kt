package com.example.assignment2mad.admin

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
import com.example.assignment2mad.students.StudentOnClickEventButton
import com.example.assignment2mad.students.StudentsDataLists


@Composable
fun AdminAddStudentData(
    //These are the declaration of a composable function is to add a student data.
    navController: NavController,
    onEventClick: (StudentOnClickEventButton) -> Unit,
    state: StudentsDataLists

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
//This "Add Student Information" text is a title of the admin add student page
            Text(text = "Add Student Information",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,

                )
            OutlinedTextField(
                value = state.fullname.value,
                onValueChange = { state.fullname.value = it},//"it" is used so that the application user can put their desired textfield
                label = { Text((stringResource(id = R.string.fullName))) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value =  state.dateofbirth.value,
                onValueChange = { state.dateofbirth.value = it},//"it" is used so that the application user can put their desired textfield
                label = { Text((stringResource(id = R.string.Dob))) },
                modifier = Modifier
                    .fillMaxWidth()

            )
            OutlinedTextField(
                value = state.semail.value,
                onValueChange = { state.semail.value = it},//"it" is used so that the application user can put their desired textfield
                label = { Text((stringResource(id = R.string.sEmail))) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.password.value,
                onValueChange = { state.password.value = it},//"it" is used so that the application user can put their desired textfield
                label = { Text((stringResource(id = R.string.studentPassword))) },
                modifier = Modifier.fillMaxWidth()
            )




            Spacer(modifier = Modifier.height(16.dp))
            Button( onClick = {
                //This clickevent helps to creata a student data
                onEventClick(
                    StudentOnClickEventButton.SaveStudentData(
                    //fullname, dateofbirth ,semail and password is in
                    // function savestudentdata where it is in used to store the list
                    // of students ,fullname,dateofbirth ,password and semail
                    fullname = state.fullname.value,
                    dateofbirth = state.dateofbirth.value,
                    semail = state.semail.value,
                    password = state.password.value,
                    addeddate = state.addeddate.value,
                    updateddate = state.updateddate.value
                ))
                //This bring You back to the main home page after adding the data of a student
                navController.popBackStack()},

                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                // Button color
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.ButtonColor),
                )){

                Text(text = "Adding Student")
            }



        }
    }

}