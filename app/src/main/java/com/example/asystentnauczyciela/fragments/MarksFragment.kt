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
import com.example.asystentnauczyciela.viewmodels.MarksAdapter
import com.example.asystentnauczyciela.viewmodels.MarksViewModel
import java.lang.StringBuilder


class MarksFragment : Fragment() {

    lateinit var viewModel: MarksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        (activity as MainActivity).supportActionBar?.title = "Marks of: " + arguments?.get("studentName") as String +
                " " +(arguments?.get("studentSurname") as String)[0] + ". " +
        " (" + arguments?.get("subjectName") + ")"
        return inflater.inflate(R.layout.student_class_marks_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = MarksViewModel((requireNotNull(this.activity).application), arguments)
        val marksListAdapter = MarksAdapter(viewModel, this.context)

        viewModel.marks.observe(viewLifecycleOwner) {
            marksListAdapter.notifyDataSetChanged()
            view.findViewById<TextView>(R.id.count_students_textview).text = buildString {
                append("Number of marks: ")
                append(viewModel.count())
            }
        }

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.marks_recyclerView).let {
            it.adapter = marksListAdapter
            it.layoutManager = layoutManager
        }

            view.findViewById<Button>(R.id.ButtonBackToStudents).apply {
                setOnClickListener {
                    val action = MarksFragmentDirections.
                    marksToStudentsAction(arguments?.get("classTag") as String, arguments?.get("subjectName") as String)
                    view.findNavController().navigate(action)
                }
            }

            view.findViewById<Button>(R.id.ButtonAddMark).apply {
                setOnClickListener {
                    val dialog = MarkAddDialogFormat(viewModel, arguments)
                    dialog.show(parentFragmentManager, "add_mark")
                }
            }
        }
    }
