package com.example.asystentnauczyciela

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    //lateinit var studentsListView: ListView
    //lateinit var textViewStudent: TextView
    //lateinit var subjectsButton: Button
    //lateinit var studentsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    //**********************************************************************************************
    //Zanim skorzystałem z nawigacji, zmieniałem fragmenty przy pomocy interfejsu i gotowych funkcji
    //**********************************************************************************************

    /*override fun passDataCom(testString: String) {

        val bundle = Bundle()
        bundle.putString("message", testString)

        val transaction = this.supportFragmentManager.beginTransaction()
        val subjectsFragment = Subjects()
        subjectsFragment.arguments = bundle
        transaction.replace(R.id.fragment_container, subjectsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun changeFragment(FragmentName: Fragment) {
        val transaction = this.supportFragmentManager.beginTransaction()
        val newFragment = FragmentName
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }*/

}