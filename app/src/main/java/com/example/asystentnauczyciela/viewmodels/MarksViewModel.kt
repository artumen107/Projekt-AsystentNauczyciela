package com.example.asystentnauczyciela.viewmodels

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.databaseModel.Mark
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDAO
import com.example.asystentnauczyciela.databaseModel.TeacherAssistantDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarksViewModel(application: Application, val arguments: Bundle?):
    AndroidViewModel(application) {

    private val dao: TeacherAssistantDAO
    init {
        dao = TeacherAssistantDatabase
            .getInstance(application).dao
    }

    fun add(mark: Mark) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertMark(mark)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteMark(id)
        }
    }

    fun count(): String {
        val count = dao.countAllMarks(arguments?.get("studentId") as Long)
        return count.toString()
    }



    val marks: LiveData<List<Mark>> = dao.getAllMarks(arguments?.get("studentId") as Long)
}