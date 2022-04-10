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
import com.example.asystentnauczyciela.viewmodels.ClassesAdapter
import com.example.asystentnauczyciela.viewmodels.ClassesViewModel


class ClassesFragment : Fragment() {
    lateinit var viewModel: ClassesViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        (activity as MainActivity).supportActionBar?.title = "Classes of: " +
                (arguments?.get("subjectName") ?: String)
        return inflater.inflate(R.layout.classes_fragment, container, false)
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ClassesViewModel((requireNotNull(this.activity).application), arguments)
        val classesListAdapter = ClassesAdapter(viewModel, this, view)

        viewModel.classes.observe(viewLifecycleOwner) {
            classesListAdapter.notifyDataSetChanged()
            view.findViewById<TextView>(R.id.count_classes_textview).text = buildString {
                append("Number of classes: ")
                append(viewModel.count())
            }
        }

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.classes_recyclerView).let {
            it.adapter = classesListAdapter
            it.layoutManager = layoutManager
        }

        //view.findViewById<TextView>(R.id.subject_name_classes).text = arguments?.get("subjectName") as String
        view.findViewById<Button>(R.id.ButtonBackToSubjects).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.classesToSubjectsAction)
            }
        }

        view.findViewById<Button>(R.id.ButtonAddClass).apply {
            setOnClickListener {
                val dialog = ClassAddDialogFragment(viewModel, arguments)
                dialog.show(parentFragmentManager, "add_class_day")
            }
        }
    }
}



