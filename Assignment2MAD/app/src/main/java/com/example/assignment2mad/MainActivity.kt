package com.example.assignment2mad

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignment2mad.admin.AdminAddStudentData
import com.example.assignment2mad.admin.AdminEditStudentData
import com.example.assignment2mad.admin.AdminLogin
import com.example.assignment2mad.admin.AdminPage
import com.example.assignment2mad.comments.CommentView
import com.example.assignment2mad.database.StudentsDBHandler
import com.example.assignment2mad.posts.PostView
import com.example.assignment2mad.students.AuthenticationForLogin
import com.example.assignment2mad.comments.StudentAddCommentInPost
import com.example.assignment2mad.posts.StudentAddPost
import com.example.assignment2mad.students.StudentEditProfile
import com.example.assignment2mad.posts.StudentEditUpdateThePost
import com.example.assignment2mad.students.StudentView
import com.example.assignment2mad.comments.StudentViewTheCommentInPost
import com.example.assignment2mad.students.UserHomePage
import com.example.assignment2mad.students.UserProfilePage
import com.example.assignment2mad.ui.theme.Assignment2MADTheme

class MainActivity : ComponentActivity() {
   //This code creates the database
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            //StudentsDBHandler is inside StudentsDBHandler.kt
            StudentsDBHandler::class.java,
            "NorthamptonSecSchDB.db" //Database name
        )

            .fallbackToDestructiveMigration() // Use this for development purposes
            .build()
    }




    //view model factory and the intances are created for the database to work
    private val viewModel by viewModels<StudentView> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<S: ViewModel> create(modelClass: Class<S>): S {
                    return StudentView(database.dao) as S



                }
            }
        }
    )
    private val postViewModel by viewModels<PostView> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<P: ViewModel> create(modelClass: Class<P>): P {
                    return PostView(database.postingFeedsDao) as P

                }
            }
        }
    )
    private val commentViewModel by viewModels<CommentView> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<C: ViewModel> create(modelClass: Class<C>): C {
                    return CommentView(database.commentingOnPostFeedsDao) as C

                }
            }
        }
    )


//Initialization of the authenticationForLogin
    private val authenticationForLogin by lazy {
    AuthenticationForLogin(database.dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Assignment2MADTheme {
                //A variable is declared here.
                //Navigation of page happens here
                //For state of student, state of post, state of comment
                val state by viewModel.state.collectAsState()
                val postState by postViewModel.postState.collectAsState()
                val commentState by commentViewModel.commentState.collectAsState()
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination?.route
                val context = LocalContext.current.applicationContext
                val selectedIcon = remember {
                    mutableStateOf(Icons.Default.Home)
                }
                Scaffold(

                    bottomBar = {
                        //IF the currentDestination is not in the login or register screen, then that means
                        // it is logged in to the User Home Page
                    if (currentDestination != "Login" && currentDestination != "Register" && currentDestination !="AdminPage"&&
                        currentDestination !="AdminLogin"&& currentDestination !="AddStudentData"&& currentDestination !="UpdateStudentData") {
                        BottomAppBar(
                            //Color of the bottom app bar
                            containerColor = colorResource(id = R.color.ButtonColor)
                        ){
                            IconButton(
                                onClick = {
                                    //This will be the initial color of the icon when it is not clicked
                                    selectedIcon.value = Icons.Default.Home
                                    //popUpTo prevents multiple back buttons clicks
                                    //It navigates to the UserHomePage page
                                    navController.navigate("UserHomePage"){popUpTo(0)}},
                                //This weight equally spaces the 3 icons
                                    modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    Icons.Default.Home,
                                    contentDescription = "Home",
                                    modifier = Modifier.size(26.dp),
                                    //tint means the color of the icon
                                    //As soon as , it is logged in, it will go to the UserHomePage which is selected as white color
                                    //and when it is unselected, it will turn to black color
                                    tint = if (selectedIcon.value == Icons.Default.Home) Color.White else Color.Black)
                            }
                            Box(
                                //Box is used to make the add button in the middle of the bottom app bar
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(14.dp),
                                contentAlignment = Alignment.Center)
                            {
                                //We use the FloatingActionButton to to highly emphasis this add button which is used to perform a primary action in an application
                                //Toast is used to check if the add button is working by sending "Open Bottom Sheet" message.
                                FloatingActionButton(onClick = {
                                    postState.descContentsPost.value = ""


                                    //Navigates to the student add post post and popUpTo prevents multiple back taps
                                    navController.navigate("StudentAddPost")
                                    Toast.makeText(context, "Add Post", Toast.LENGTH_SHORT).show()
                                }){
                                    Icon(
                                        //Icon for adding post
                                        imageVector = Icons.Default.Add, contentDescription = "Add Post", tint = Color.Blue)

                                }
                            }

                            IconButton(
                                onClick = {
                                    //This will be the initial color of the icon when it is not clicked
                                    selectedIcon.value = Icons.Default.Person
                                    //popUpTo prevents multiple back buttons clicks
                                    //It navigates to the UserProfilePage page
                                    navController.navigate("UserProfilePage"){popUpTo(0)}},
                                //This weight equally spaces the 3 icons
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Profile",
                                    modifier = Modifier.size(26.dp),
                                    //As soon as , the profile icon is selected, it turns to white color
                                    //and when it is unselected, it will turn to black color
                                    tint = if (selectedIcon.value == Icons.Default.Person) Color.White else Color.Black)
                            }

                        }
                    }
                }) { innerPadding ->


                //the destination always starts with the Login page
                    NavHost(navController = navController, startDestination = "Login") {
                        composable("Login") {
                            Login(
                                navController = navController,
                                // authenticationForLogin is called after being initialized
                                viewModel = authenticationForLogin

                            )
                        }
                        //This navigates to the user home page
                        composable("UserHomePage") {
                            val student = authenticationForLogin.loginCondition
                            if(student !=null) {
                                UserHomePage(
                                    student = student,
                                    navController = navController,
                                    postState = postState,
                                    commentState = commentState,
                                    //use onEventClickForPost so we want to know when the user do the action on the icon buttons
                                    onEventClickForPost = postViewModel::onTheEventButtonForPost,

                                )
                            }
                        }
                        //This navigates to the user profile page
                        composable("UserProfilePage") {
                            val student = authenticationForLogin.loginCondition
                            if(student !=null) {
                                UserProfilePage(
                                    // The state and the event click button is called here
                                    state = state,
                                    navController = navController,
                                    onEventClick = viewModel::onTheEventButton,
                                    student = student

                                )
                            }

                        }

                        //This navigates to the StudentAddPost page for adding their post
                        composable("StudentAddPost") {
                            val student = authenticationForLogin.loginCondition
                            if(student !=null) {
                                StudentAddPost(
                                    postState = postState,
                                    navController = navController,
                                    //use onEventClickForPost so we want to know when the user do the action on the icon buttons
                                    onEventClickForPost = postViewModel::onTheEventButtonForPost
                                )
                            }
                        }

                        //This navigates to the StudentAddPost page for adding their post
                        composable("StudentEditUpdateThePost") {
                            val student = authenticationForLogin.loginCondition
                            if(student !=null) {
                                StudentEditUpdateThePost(
                                    postState = postState,
                                    navController = navController,
                                    //use onEvent so we want to know when the user do the action on the icon buttons
                                    onEventClickForPost = postViewModel::onTheEventButtonForPost
                                )
                            }
                        }

                        //This navigates to the page where users add comments of the post
                        composable("StudentAddCommentInPost") {

                            val student = authenticationForLogin.loginCondition
                            if(student !=null) {
                                StudentAddCommentInPost(
                                    navController = navController,
                                    commentState = commentState,
                                    //use onEventClickForComment so we want to know when the user do the action on the icon buttons
                                    onEventClickForComment = commentViewModel::onTheEventButtonForComment
                                )
                            }
                        }


                        //This navigates to the user home page
                        composable("StudentViewTheCommentInPost") {
                            val student = authenticationForLogin.loginCondition
                            if(student !=null) {
                                StudentViewTheCommentInPost(
                                    student = student,
                                    navController = navController,
                                    commentState = commentState,
                                    //use onEventClickForComment so we want to know when the user do the action on the icon buttons
                                            onEventClickForComment = commentViewModel::onTheEventButtonForComment
                                )

                            }
                        }
                        //This navigates to the AdminLogin
                        composable("AdminLogin") {
                            AdminLogin(navController)

                        }
                        //This navigates to the AdminPage
                        composable("AdminPage") {
                            AdminPage(
                                // The state and the event click button is called here
                                state = state,
                                navController = navController,
                                onEventClick = viewModel::onTheEventButton

                            )

                        }
                        //This navigates to the AddStudentData page for adding student data
                        composable("AddStudentData") {
                            AdminAddStudentData(
                                state = state,
                                navController = navController,
                                //use onEvent so we want to know when the user do the action on the icon buttons
                                onEventClick = viewModel::onTheEventButton)

                        }


                        //This navigates to the UpdateStudentData page for updating student data
                        composable("UpdateStudentData") {
                            AdminEditStudentData(
                                state = state,
                                navController = navController,
                                //use onEvent so we want to know when the user do the action on the icon buttons
                                onEventClick = viewModel::onTheEventButton)

                        }


                        //This navigates to the StudentEditProfile page for updating their own data
                        composable("StudentEditProfile") {
                            StudentEditProfile(
                                state = state,
                                navController = navController,
                                //use onEvent so we want to know when the user do the action on the icon buttons
                                onEventClick = viewModel::onTheEventButton)

                        }
                        //This navigates to the register page for user registration
                        composable("Register") {
                            Register(
                                state = state,
                                navController = navController,
                                //use onEvent so we want to know when the user do the action on the icon buttons
                                onEventClick = viewModel::onTheEventButton
                            )


                        }

                    }
                }
            }
        }
    }
}

