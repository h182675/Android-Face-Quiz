package com.android.oblig.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.widget.Toast
import com.android.oblig.R
import com.android.oblig.modules.Person

class AddPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        //Access the input and button fields
        val etInput = findViewById<TextInputEditText>(R.id.navnInputId)
        val submit = findViewById<Button>(R.id.buttonId)

        //When button is clicked...
        if(submit != null && etInput != null ) {
            submit.setOnClickListener {
                val input = etInput.text.toString()
                //add picture and name to the database
            }
        }
        //var p = etInput.toString()

        //Add person to the database
        fun addPerson(p: String): String {

            return p
        }
    }

}
