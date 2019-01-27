package com.android.oblig.activities


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.oblig.R
import com.android.oblig.modules.Person


class PersonList : AppCompatActivity() {

    var personList:MutableList<Person> = ArrayList<Person>()


    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        // Get the person list from db
        personList = MainMenu.db.personDao().getAll() as MutableList<Person>

        var personNames = mutableListOf<String>()
        for(i in 0 until personList.size){
            personNames.add(personList[i].name)
        }

        // Get view from id
        var listView:ListView = findViewById(R.id.person_list)

        // Create adapter
        var personAdapter = PersonAdapter(this,personList)

        // Link adapter to view
        listView.adapter = personAdapter

        var addPersonBtn:Button = findViewById<Button>(R.id.add_person_btn)
        addPersonBtn.setOnClickListener {
            val intent = Intent(this,AddPerson::class.java)
            startActivity(intent)
        }
    }
}

class PersonAdapter(private val context: Context,
                    private val dataSource:MutableList<Person>):BaseAdapter(){

    private val inflater: LayoutInflater
        = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.person_item,parent,false)

        // Find item objects
        val personImageView = rowView.findViewById<ImageView>(R.id.person_item_image)
        val personNameView = rowView.findViewById<TextView>(R.id.person_item_name)
        val personDeleteBtn = rowView.findViewById<Button>(R.id.person_item_deleteBtn)

        // Get person
        val person = getItem(position) as Person

        // Populate view objects
        // Convert bitmap from bytearray
        // Set default image for now
        //TODO: Get image from byteArray
        val bitmapImage = ContextCompat.getDrawable(context,R.drawable.defaultpicture)
        personImageView.setImageDrawable(bitmapImage)
        // Name
        val personName:String = person.name
        personNameView.text = personName
        // Button
        personDeleteBtn.setOnClickListener{
            //TODO: Implement DeleteBtn:Remove person from list and db
        }



        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }

}

