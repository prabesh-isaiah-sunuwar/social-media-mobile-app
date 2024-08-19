package com.example.assignment2mad.students

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2mad.database.Student
import com.example.assignment2mad.database.StudentSlashUserDao
import kotlinx.coroutines.launch

//class is sealed to represent the fields which is the semail which means school email and password
sealed class AuthenticationActionEvent {
    data class Login(val semail: String, val password: String) : AuthenticationActionEvent()
}

//AuthenticationForLogin class is declared at first which this is inherited from ViewModel
//studentSlashUserDao instance is taken as a parameter to link with the data which is in the database
class AuthenticationForLogin(private val studentSlashUserDao: StudentSlashUserDao) : ViewModel() {
//_loginCondition is a private mutable state which holds the loginCondition
    private val _loginCondition = mutableStateOf<Student?>(null)
    //loginCondition is a public immutable state that exposes the _loginCondition
    val loginCondition: Student? get() = _loginCondition.value
//onTheEventOf function is created so that we need to link with the authentication
    fun onTheEventOf(theeventhappens: AuthenticationActionEvent) {
        when (theeventhappens) {
//AuthenticationActionEvent contains the data class which is Login
            is AuthenticationActionEvent.Login -> {
                //we use  viewModelScope.launch  to manage resources which means that the viewModelScope is automatically cancelled when the viewModel is clear

                viewModelScope.launch {
                    //first of all we need StudentSlashUserDao to interact with the
                    // getStudentsSemailAndPassword query with the database which is semail and password which we need.
                    studentSlashUserDao.getStudentsSemailAndPassword(
                        theeventhappens.semail,
                        theeventhappens.password
                    ).collect { student ->
                        _loginCondition.value = student
                    }
                }
            }

        }
    }
}