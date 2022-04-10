package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.MainActivity
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodels.StudentsAdapter
import com.example.asystentnauczyciela.viewmodels.StudentsViewModel
import org.w3c.dom.Text


class StudentsFragment : Fragment() {

    lateinit var viewModel: StudentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        (activity as MainActivity).supportActionBar?.title = "Students of class: " +
                (arguments?.get("classTag") ?: String)

        return inflater.inflate(R.layout.students_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = StudentsViewModel((requireNotNull(this.activity).application), arguments)
        val studentsAdapter = StudentsAdapter(view, viewModel, this.context)

        viewModel.students.observe(viewLifecycleOwner) {
            studentsAdapter.notifyDataSetChanged()
            view.findViewById<TextView>(R.id.count_students_textview).text = buildString {
                append("Number of students: ")
                append(viewModel.count())

            }
        }

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.students_recyclerView).let {
            it.adapter = studentsAdapter
            it.layoutManager = layoutManager
        }


        view.findViewById<Button>(R.id.ButtonBackToClasses).apply {
            setOnClickListener {
                val action = StudentsFragmentDirections
                    .studentsToClassesAction(arguments?.get("subjectName") as String)
                view.findNavController().navigate(action)

            }
        }

        view.findViewById<Button>(R.id.ButtonAddStudent).apply {
            setOnClickListener {
                val dialog = StudentAddDialogFragment(viewModel, arguments?.get("classTag") as String, arguments?.get("subjectName") as String)
                dialog.show(parentFragmentManager, "add_student")

            }
        }
    }
}

