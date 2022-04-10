package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDAO
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.asystentnauczyciela.databaseModel.Class

class ClassesViewModel(application: Application, val arguments: Bundle?) :
    AndroidViewModel(application) {

    private val dao: TeacherAssistantDAO

    init {
        dao = TeacherAssistantDatabase
            .getInstance(application).dao
    }

    fun add(myClass: Class) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertClass(myClass)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteClass(id)
        }
    }

    fun get(id: Long): Class {
        return dao.getClass(id)
    }

    fun count(): String {
        val count = dao.countAllClasses(arguments?.get("subjectName").toString())
        return count.toString()
    }

    val classes: LiveData<List<Class>> = dao.getAllClasses(arguments?.get("subjectName") as String)

}

