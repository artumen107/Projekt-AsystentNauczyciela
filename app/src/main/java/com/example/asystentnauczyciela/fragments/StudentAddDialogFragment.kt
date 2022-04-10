package com.example.asystentnauczyciela.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.databaseModel.Student
import com.example.asystentnauczyciela.viewmodels.StudentsViewModel


class StudentAddDialogFragment(private val viewModel: StudentsViewModel,
                               private val classTagArgument: String,
                               private val subjectNameArgument: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView: View = inflater.inflate(R.layout.add_student, null)
        val nameInput: EditText = dialogView.findViewById(R.id.NameEditText)
        val surnameInput: EditText = dialogView.findViewById(R.id.SurnameEditText)
        val albumNumberInput: EditText = dialogView.findViewById(R.id.AlbumNumberEditText)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    if (nameInput.text.isNotEmpty() and surnameInput.text.isNotEmpty() and albumNumberInput.text.isNotEmpty()) {
                        viewModel.add(
                            Student(0, classTagArgument,
                            nameInput.text.toString(), surnameInput.text.toString(),(albumNumberInput.text.toString()).toLong(), subjectNameArgument)
                        )
                    }
                }

                .setNegativeButton("Cancel") { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}