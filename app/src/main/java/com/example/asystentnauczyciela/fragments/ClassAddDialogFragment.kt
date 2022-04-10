package com.example.asystentnauczyciela.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodels.ClassesViewModel
import com.example.asystentnauczyciela.databaseModel.Class


class ClassAddDialogFragment(private val viewModel: ClassesViewModel,
                             private val subjectNameArgument: Bundle?) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView: View = inflater.inflate(R.layout.add_class, null)
        val spinner: Spinner = dialogView.findViewById(R.id.day_spinner)
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.weekDays,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        val groupTagInput: EditText = dialogView.findViewById(R.id.groupTag_input)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    viewModel.add(Class(0,
                        subjectNameArgument?.get("subjectName") as String,
                        spinner.selectedItem as String,
                        groupTagInput.text.toString()
                        ))
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


