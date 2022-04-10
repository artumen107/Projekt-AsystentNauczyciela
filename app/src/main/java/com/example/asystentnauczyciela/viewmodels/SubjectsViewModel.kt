package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDAO
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDatabase
import com.example.asystentnauczyciela.databaseModel.Subject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SubjectsViewModel(application: Application):
    AndroidViewModel(application) {

    private val dao: TeacherAssistantDAO
    init {
        dao = TeacherAssistantDatabase
            .getInstance(application).dao
    }

    fun add(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertSubject(subject)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllSubjects()
        }
    }

    fun delete(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteSubject(name)
        }
    }

    fun count(): String {
        val count = dao.countAllSubjects()
        return count.toString()
    }

    val subjects:LiveData<List<Subject>> = dao.getAllSubjects()

}