package com.example.asystentnauczyciela.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.fragments.StudentsFragmentDirections

class StudentsAdapter(private val view: View,
                          private val viewModel: StudentsViewModel,
                          private val context: Context?)
    : RecyclerView.Adapter<StudentsAdapter.StudentsListHolder>() {

    inner class StudentsListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewStudentData: TextView = view.findViewById(R.id.student_data)
        val buttonViewStudentOption: ImageButton = view.findViewById(R.id.student_options)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_student, parent, false)
        return StudentsListHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsListHolder, position: Int) {
        holder.textViewStudentData.text = buildString {
            append(viewModel.students.value?.get(position)?.name + " ")
            append(viewModel.students.value?.get(position)?.surname + " #")
            append(viewModel.students.value?.get(position)?.albumNumber)

        }



        holder.textViewStudentData.setOnClickListener {
            val action = StudentsFragmentDirections
                .studentsToMarksAction(viewModel.students.value?.get(position)?.id!!,
                        viewModel.students.value?.get(position)?.name as String,
                    viewModel.students.value?.get(position)?.parentClassTag as String,
                    viewModel.students.value?.get(position)?.albumNumber as Long,
                    viewModel.students.value?.get(position)?.parentSubjectName as String,
                    viewModel.students.value?.get(position)?.surname as String
                    )
            view.findNavController().navigate(action)
        }


        holder.buttonViewStudentOption.setOnClickListener {
            val popup = PopupMenu(context, holder.buttonViewStudentOption)
            popup.inflate(R.menu.student_options_menu)
            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.student_delete -> {
                        viewModel.delete(viewModel.students.value?.get(position)?.name.toString(), viewModel.students.value?.get(position)?.surname.toString(),
                            viewModel.students.value?.get(position)?.albumNumber!!.toLong())
                    }

                }
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return viewModel.students.value?.size ?: 0
    }
}