package com.example.asystentnauczyciela.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.MainActivity
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.viewmodels.SubjectsAdapter
import com.example.asystentnauczyciela.viewmodels.SubjectsViewModel


class SubjectsFragment : Fragment() {

    lateinit var viewModel: SubjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        (activity as MainActivity).supportActionBar?.title = "My subjects"
        return inflater.inflate(R.layout.subjects_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = SubjectsViewModel((requireNotNull(this.activity).application))
        val subjectsAdapter = SubjectsAdapter(view, viewModel, viewModel.subjects, this.context)

        viewModel.subjects.observe(viewLifecycleOwner) {
            subjectsAdapter.notifyDataSetChanged()
                view.findViewById<TextView>(R.id.count_subjects_textview).text = buildString {
                    append("Number of subjects: ")
                    append(viewModel.count())

            }
        }

        val layoutManager = LinearLayoutManager(view.context)
        view.findViewById<RecyclerView>(R.id.subjects_recyclerView).let {
            it.adapter = subjectsAdapter
            it.layoutManager = layoutManager
        }


        view.findViewById<Button>(R.id.ButtonAddSubject).apply {
            setOnClickListener {
               val dialog = SubjectAddDialogFragment(viewModel)
               dialog.show(parentFragmentManager, "add_subject")
            }
        }

    }
}