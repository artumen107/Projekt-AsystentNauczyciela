package com.example.asystentnauczyciela.viewmodels

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.databaseModel.Subject
import com.example.asystentnauczyciela.fragments.SubjectsFragmentDirections


class SubjectsAdapter(private val view: View,
                          private val viewModel: SubjectsViewModel,
                          private val subjects: LiveData<List<Subject>>,
                          private val context: Context?)
    : RecyclerView.Adapter<SubjectsAdapter.SubjectsListHolder>() {

    inner class SubjectsListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewSubjectName: TextView = view.findViewById(R.id.subject_name)
        val buttonViewSubjectOption: ImageButton = view.findViewById(R.id.subject_options)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_subject, parent, false)
        return SubjectsListHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectsListHolder, position: Int) {
        holder.textViewSubjectName.text = subjects.value?.get(position)?.subjectName
        holder.textViewSubjectName.setOnClickListener {
            val action = SubjectsFragmentDirections
            .subjectsToClassesAction(holder.textViewSubjectName.text.toString())
            view.findNavController().navigate(action)
        }

        holder.buttonViewSubjectOption.setOnClickListener {
            val popup = PopupMenu(context, holder.buttonViewSubjectOption)
            popup.inflate(R.menu.subject_options_menu)
            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.subject_delete -> {
                        viewModel.delete(holder.textViewSubjectName.text.toString())
                    }

                }
                true
            }
            popup.show()
        }
    }

    override fun getItemCount(): Int {
        return subjects.value?.size ?: 0
    }
}