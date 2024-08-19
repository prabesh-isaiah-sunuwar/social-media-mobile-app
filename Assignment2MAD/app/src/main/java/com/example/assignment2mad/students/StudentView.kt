package com.example.assignment2mad.students

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2mad.database.StudentSlashUserDao
import com.example.assignment2mad.database.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//This is the student view model
class StudentView(
    private val dao: StudentSlashUserDao

): ViewModel() {
    //tell us whether the variable that tells us whether our student data is sorted according to the  date as being added
// or by school email
    //want the student data to be the ordered by as they are added not by the email until the students changes it
    private val sortedOutByTheDateAdded = MutableStateFlow(true)
    //defining a variable currentStudentDataEdit which is to stores the latest updated student data
    private var currentStudentDataEdit: Student? = null


    private var students =
        //This fines a way to get the notes from the database which then
        // sorts based on the type of the data and the getStudentDataOrderedByDateAdded
        sortedOutByTheDateAdded.flatMapLatest { sort->
            if(sort){
                dao.getStudentDataOrderedByDateAdded()
            }else{
                dao.getStudentsDataOrderedBySchoolEmailAlphabetically()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    //this MutableStateFlow(studentdataLists())is used to track the studentdataLists()
    val _stateOfTheStudentData = MutableStateFlow(StudentsDataLists())
    val state =

        combine(_stateOfTheStudentData, sortedOutByTheDateAdded, students) { state, isSortedByTheDateAdded, students ->
            state.copy(
                //the students data are equals to the new students data
                students = students

            )

        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), StudentsDataLists())



    fun onTheEventButton(event: StudentOnClickEventButton){
        when (event){
            //we need to have info whether the users want to delete the data
            //launches a coroutine to delete the Students data
            is StudentOnClickEventButton.DeleteStudentData -> {
                viewModelScope.launch{
                    //As deleteStudent() was a suspend function, we launch the viewModelScope
                    dao.deleteStudentData(event.student)
                }
            }
//The saveStudentData aids in the creating or updating and then clears the input field once the add student button or the update student button is clicked.
            //The latest updated data will occur or if it is a new student then a new student data will be created.
            is StudentOnClickEventButton.SaveStudentData -> {
                val student = currentStudentDataEdit?.copy(
                    fullname = state.value.fullname.value,
                    dateofbirth = state.value.dateofbirth.value,//Saves the student data upon the addition or editing
                    semail = state.value.semail.value,
                    password = state.value.password.value, //Saves the student data upon the addition or editing
                    addeddate = System.currentTimeMillis(),
                    updateddate = System.currentTimeMillis()
                ) ?: Student(
                    fullname = state.value.fullname.value,//Saves the student data upon the addition or editing
                    dateofbirth = state.value.dateofbirth.value,
                    semail = state.value.semail.value,//Saves the student data upon the addition or editing
                    password = state.value.password.value,
                    addeddate = System.currentTimeMillis(),
                    updateddate = System.currentTimeMillis()
                )
                viewModelScope.launch {
                    //this is lauched to add or edit the student data
                    dao.upsertStudentData(student)
                }

                _stateOfTheStudentData.update {
                    it.copy(
                        //sets the fields to be an empty as we do not want the fields to be filled
                        // with any value because it should be  already saved in the room database
                        fullname = mutableStateOf(""),
                        dateofbirth = mutableStateOf(""),
                        semail = mutableStateOf(""),
                        password = mutableStateOf(""),
                        addeddate =mutableStateOf(System.currentTimeMillis()),
                        updateddate =mutableStateOf(System.currentTimeMillis())

                    )
                }
                currentStudentDataEdit = null

            }
            //.Updatestudentdata used to edit the current student data into a new student data
            is StudentOnClickEventButton.UpdateStudentData -> {
                currentStudentDataEdit = event.student
                _stateOfTheStudentData.update {
                    it.copy(
                        //Saves the student data upon the addition or editing
                        fullname = mutableStateOf(event.student.fullname),
                        dateofbirth = mutableStateOf(event.student.dateofbirth),
                        semail = mutableStateOf(event.student.semail),
                        password = mutableStateOf(event.student.password),
                        addeddate = mutableStateOf(System.currentTimeMillis()),
                        updateddate = mutableStateOf(System.currentTimeMillis())
                    )
                }
            }


            StudentOnClickEventButton.SortStudentData -> {
                sortedOutByTheDateAdded.value = !sortedOutByTheDateAdded.value

            }
        }

    }



}