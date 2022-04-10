package com.example.asystentnauczyciela.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.databaseModel.Mark
import com.example.asystentnauczyciela.viewmodels.MarksViewModel

class MarkAddDialogFormat(private val viewModel: MarksViewModel,
                          private val myArguments: Bundle?) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val dialogView: View = inflater.inflate(R.layout.add_mark, null)
        val markInput: EditText = dialogView.findViewById(R.id.mark_input)
        val descriptionInput: EditText = dialogView.findViewById(R.id.mark_desc_input)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(dialogView)
                .setPositiveButton("Add") { _, _ ->
                    if (markInput.text.isNotEmpty()) {
                        println(markInput.text.toString())
                        viewModel.add(Mark(0, markInput.text.toString().toFloat(), descriptionInput.text.toString(), myArguments?.get("studentId") as Long))
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}