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
import com.example.asystentnauczyciela.fragments.ClassesFragment
import com.example.asystentnauczyciela.fragments.ClassesFragmentDirections
import com.example.asystentnauczyciela.fragments.SubjectsFragmentDirections
import org.w3c.dom.Text

class ClassesAdapter(private val viewModel: ClassesViewModel,
                         private val parentFragment: ClassesFragment,
                     private val view: View
)
    : RecyclerView.Adapter<ClassesAdapter.ClassesListHolder>() {

    inner class ClassesListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewClassDayOfWeek: TextView = view.findViewById(R.id.class_day_of_week)
        val textViewGroupTag: TextView = view.findViewById(R.id.class_group_tag)
        val buttonViewClassOption: ImageButton = view.findViewById(R.id.class_options)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesListHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_class, parent, false)
        return ClassesListHolder(view)
    }
    override fun onBindViewHolder(holder: ClassesListHolder, position: Int) {
        holder.textViewClassDayOfWeek.text = viewModel.classes.value?.get(position)?.dayWeek.toString()
        holder.textViewGroupTag.text = buildString {
            append(viewModel.classes.value?.get(position)?.classTag)
        }
        holder.buttonViewClassOption.setOnClickListener {
            val popup = PopupMenu(parentFragment.context, holder.buttonViewClassOption)
            popup.inflate(R.menu.class_options_menu)
            popup.setOnMenuItemClickListener { item: MenuItem? ->
                when (item!!.itemId) {
                    R.id.class_delete -> {
                        viewModel.delete(viewModel.classes.value?.get(position)?.id!!)
                    }

                }
                true
            }
            popup.show()
        }

        holder.textViewGroupTag.setOnClickListener {
            val action = ClassesFragmentDirections
                .classesToStudentsAction(viewModel.classes.value?.get(position)?.classTag.toString(), viewModel.classes.value?.get(position)?.parentSubjectName.toString())
            view.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return viewModel.classes.value?.size ?: 0
    }
}