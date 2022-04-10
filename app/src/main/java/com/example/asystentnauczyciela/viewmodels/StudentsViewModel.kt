package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.databaseModel.Student
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDAO
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentsViewModel(application: Application, val arguments: Bundle?):
    AndroidViewModel(application) {

    private val dao: TeacherAssistantDAO
    init {
        dao = TeacherAssistantDatabase
            .getInstance(application).dao
    }

    fun add(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertStudent(student)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllStudents()
        }
    }

    fun delete(name: String, surname: String, albumNumber: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteStudent(name, surname, albumNumber)
        }
    }
    fun count(): String {
        val count = dao.countAllStudents(arguments?.get("classTag") as String)
        return count.toString()
    }
    val students: LiveData<List<Student>> = dao.getAllStudents(arguments?.get("classTag") as String)
}