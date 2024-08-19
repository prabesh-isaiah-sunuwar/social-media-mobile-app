package com.example.assignment2mad

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment2mad.students.AuthenticationActionEvent
import com.example.assignment2mad.students.AuthenticationForLogin

@Composable
fun Login(
    navController: NavController,
    viewModel: AuthenticationForLogin,
) {

    //It indicates the action that has been done after logging in
    val context = LocalContext.current.applicationContext
    var semail by remember { mutableStateOf("") }
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

        //Depicts a school logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace with your logo drawable resource
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth() // Adjust size as needed
        )
        //This Spacer leaves a space between the two layouts which is the "School Logo" and ""Login with your school email"".
        Spacer(modifier = Modifier.height(40.dp))

        Text("Login with your school email", fontSize = 12.sp , fontWeight = FontWeight.ExtraBold)
//OutlinedTextField of School email
        OutlinedTextField(
            value = semail,
            onValueChange = { semail = it },
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

            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
            }

        )
        //Distance between the email and the password container is 8dp
        Spacer(modifier = Modifier.height(8.dp))
        //OutlinedTextField of School email
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
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
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            }//This is an icon

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                //onTheEventOf is called to link with the sealed class AuthenticationActionEvent
                viewModel.onTheEventOf(AuthenticationActionEvent.Login(semail, password))
                val student = viewModel.loginCondition
                //If the student which consist of the email and password fields is not equals to zero, you are logged in. zero meaning null
                if (student != null) {
                    //navigates to User page if the school email and password matches the one that is inside the database
                    navController.navigate("UserHomePage")
                    //Toast will appear will appear welcoming the user.
                    Toast.makeText(context, "Hello User", Toast.LENGTH_SHORT).show()


                } else{
                    //If the email and password is wrong a red word will appear saying ,"Invalid Email or Password. Try again" which is true
                    errorInLogin = true
                } },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            //Button color
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.ButtonColor),
            )

        ) {
            //Button for the user meaning the students login
            Text("Login")
        }
        //Text if students forget their password
        Text("Contact Admin if password is forgotten", fontSize = 12.sp , fontWeight = FontWeight.ExtraBold)
        if(errorInLogin){
            Text("Invalid Email or Password. Try again", color = Color.Red, fontSize = 9.sp , fontWeight = FontWeight.ExtraBold)
        }
        Spacer(modifier = Modifier.height(10.dp))

//This is the divider where it separates the top and the bottom contents
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), color = Color.Gray, thickness = 1.dp
            )
            //This is the divider where it separates the top and the bottom contents
            Text(text = "Or")
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), color = Color.Gray, thickness = 1.dp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
//Button for the admin login
        Button(
            //navigates to admin login page upon clicking the button
            onClick = { navController.navigate("AdminLogin") },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            //button colour
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.ButtonColor),
            )
        ) {
            ////Button for the admin login
            Text("Login as Admin")
        }

        Spacer(modifier = Modifier.height(25.dp))
        //Button for the registration of account for the students
        Button(
            //navigates to register page upon clicking the button
            onClick = { navController.navigate("Register") },
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.ButtonColor),
            )
        ) {
            //Button for the user registration

            Text("Register an account")
        }
        Spacer(modifier = Modifier.height(16.dp))
        //Text saying Northampton sec school
        Text("Northampton Secondary School", fontSize = 15.sp , fontWeight = FontWeight.Bold, color =  colorResource(id = R.color.Northampton))
    }
}

