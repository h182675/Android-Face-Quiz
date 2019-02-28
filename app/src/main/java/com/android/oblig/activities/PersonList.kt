package com.android.oblig.activities


import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.oblig.R
import com.android.oblig.modules.Person
import com.android.oblig.modules.PersonUtil


class PersonList : AppCompatActivity() {

    var personList:MutableList<Person> = ArrayList<Person>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        var addPersonBtn:Button = findViewById<Button>(R.id.add_person_btn)
        addPersonBtn.setOnClickListener {
            val intent = Intent(this, AddPerson::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        populateListView();

    }

    private fun populateListView(){
        // Get the person list from db
        personList = MainMenu.db.personDao().getAll() as MutableList<Person>

        var personNames = mutableListOf<String>()
        for(i in 0 until personList.size){
            personNames.add(personList[i].name)
        }

        // Get view from id
        var listView:ListView = this.findViewById(R.id.person_list)

        // Create adapter
        var personAdapter = PersonAdapter(this,personList)

        // Link adapter to view
        listView.adapter = personAdapter
    }
}

class PersonAdapter(private val context: Context,
                    private val dataSource:MutableList<Person>):BaseAdapter(){

    private lateinit var holder:ViewHolder

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var rowView = convertView

        // If view has never been used before, create it
        if(convertView == null){
            val inflater: LayoutInflater
                    = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.person_item,parent,false)
            holder = ViewHolder(
                rowView.findViewById(R.id.person_item_image),
                rowView.findViewById(R.id.person_item_name),
                rowView.findViewById(R.id.person_item_deleteBtn)
            )
            rowView.tag = holder
        }
        // Else if it has been used before, re-use it
        else{
            holder = convertView.tag as ViewHolder
        }

        // Get person
        val person = getItem(position)

        // Populate views objects
        // Convert bitmap from bytearray
        holder.personImageView.setImageBitmap(PersonUtil.byteArrayToBitmap(person.picture))
        // Name
        holder.personNameView.text = person.name
        // Button
        holder.personDeleteBtn.setOnClickListener{
            PersonAdapterHelpers(dataSource).delete(person)
            this.notifyDataSetChanged()
        }


        return rowView

    }

    private data class ViewHolder @JvmOverloads constructor(
        val personImageView:ImageView,
        val personNameView:TextView,
        val personDeleteBtn:Button
    )

    override fun getItem(position: Int): Person {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }



}

class PersonAdapterHelpers(val dataSource: MutableList<Person>){
    fun delete(person: Person){
        deletePersonFromList(person)
        deletePersonFromDB(person)
    }

    fun deletePersonFromList(person: Person){
        dataSource.remove(person)
    }

    fun deletePersonFromDB(person: Person){
        MainMenu.db.personDao().delete(person)
    }
}

