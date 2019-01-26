package com.android.oblig.activities


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.oblig.R
import com.android.oblig.modules.Person


class PersonList : AppCompatActivity() {

    var listItems:MutableList<Person> = ArrayList<Person>()


    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        // Get the person list from db
        listItems = MainMenu.db.personDao().getAll() as MutableList<Person>

        var personNames = mutableListOf<String>()
        for(i in 0 until listItems.size){
            personNames.add(listItems[i].name)
        }

        // Get view from id
        var listView:ListView = findViewById(R.id.person_list)

        // Create adapter
        var personAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,personNames)

        // Link adapter to view
        listView.adapter = personAdapter

        var addPersonBtn:Button = findViewById<Button>(R.id.add_person_btn)
        addPersonBtn.setOnClickListener {
            val intent = Intent(this,AddPerson::class.java)
            startActivity(intent)
        }
    }
}


