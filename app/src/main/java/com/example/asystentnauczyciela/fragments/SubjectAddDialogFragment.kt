package com.example.asystentnauczyciela.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.databaseModel.Subject
import com.example.asystentnauczyciela.viewmodels.SubjectsViewModel


class SubjectAddDialogFragment(private val viewModel: SubjectsViewModel) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView: View = inflater.inflate(R.layout.add_subject, null)
        val subjectInput: EditText = dialogView.findViewById(R.id.subject_input)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(dialogView)
                .setPositiveButton("Add new subject") { _, _ ->
                    if (subjectInput.text.isNotEmpty()) {
                        viewModel.add(Subject(0, subjectInput.text.toString()))
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}