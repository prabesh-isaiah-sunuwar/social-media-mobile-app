package com.example.assignment2mad.admin

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import com.example.assignment2mad.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminLogin(

    navController: NavHostController


) {
    //declaration of a varible
    //It indicates the action that has been done after logging in
    val context = LocalContext.current.applicationContext
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorInLogin by remember { mutableStateOf(false) }
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
        //Title of this page is Admin login
        TopAppBar(
            title = { Text(text = "Admin Login", fontSize = 29.sp, fontWeight = FontWeight.SemiBold) }
        )
//OutlinedTextField of a username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text((stringResource(id = R.string.adminUsername)) )},
            colors = TextFieldDefaults.colors(
                //When the text field is clicked the icon color will change to DarkGrayish
                focusedLeadingIconColor =  colorResource(id = R.color.greyishBlack),
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
                focusedIndicatorColor =colorResource(id = R.color.greyishBlack),
                unfocusedIndicatorColor = Color.LightGray

            ), leadingIcon = {
                //Icon of a person
                Icon(imageVector = Icons.Default.Person, contentDescription = "username")
            }

        )
        //distance between the username and password fields are 8dp
        Spacer(modifier = Modifier.height(8.dp))
        //OutlinedTextField of a password

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.adminPassword)) },
            visualTransformation = PasswordVisualTransformation(),//this line is it makes the plain text password in dot
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
//lock icon
            ), leadingIcon = {
                //Lock icon
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // the username and password are spelled admin then it is navigates in to the admin panel
                if (username == "admin" && password == "admin"){
                    navController.navigate("AdminPage")
                    Toast.makeText(context, "Welcome Admin", Toast.LENGTH_SHORT).show()


                } else{
                    errorInLogin = true//else an error message with be shown if wrong username and password are keyed in
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            //button color
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.ButtonColor),
            )

        ) {
            Text("Login")
        }
        if(errorInLogin){
            // text for the errorInLogin
            Text("Input of username or password maybe wrong. Try again", color = Color.Red)
        }
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            //goes back to the user login page upon clicking this button
            onClick = { navController.navigate("Login")},
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(//button color
                colorResource(id = R.color.ButtonColor),
            )
        ) {
            Text("Go Back to User Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Northampton Secondary School", fontSize = 15.sp , fontWeight = FontWeight.Bold, color = colorResource(id = R.color.Northampton))
    }
}

