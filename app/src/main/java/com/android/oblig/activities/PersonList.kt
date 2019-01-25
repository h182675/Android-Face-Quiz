package com.android.oblig.activities


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.oblig.R
import com.android.oblig.modules.Person


class PersonList : AppCompatActivity() {

    var listItems:MutableList<Person> = ArrayList<Person>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        // Create view objects
        var listView:ListView = findViewById<ListView>(R.id.person_list)
        var addPersonBtn:Button = findViewById<Button>(R.id.add_person_btn)

        // Get the person list from db
        listItems = MainMenu.db.personDao().getAll() as MutableList<Person>

        var p1:Person = Person(1,"guy",null)
        listItems.add(p1)

        // Create adapter
        var adapter = ArrayAdapter<Person>(this,android.R.layout.simple_list_item_1,listItems)


        // Link adapter to view
        listView.adapter

        addPersonBtn.setOnClickListener {
            val intent = Intent(this,AddPerson::class.java)
            startActivity(intent)
        }
    }

    fun addItems(view:View){

    }

}

