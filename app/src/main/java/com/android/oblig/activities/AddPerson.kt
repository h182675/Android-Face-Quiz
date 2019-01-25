package com.android.oblig.activities


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.android.oblig.R


class AddPerson : AppCompatActivity() {

    companion object{
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)

        /*
        Access the input and button fields
         */
        val etInput = findViewById<TextInputEditText>(R.id.navnInputId)
        val submit = findViewById<Button>(R.id.buttonId)
        val choose = findViewById<Button>(R.id.chooseImageId)

        fun selectImageAlbum(){
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if(intent.resolveActivity(packageManager) != null){
                startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)

            }
        }


        //When choose image button is clicked...
        if(choose != null){
            choose.setOnClickListener{

            }
        }
        //When add button is clicked...
        if (submit != null && etInput != null) {
            submit.setOnClickListener {
                val input = etInput.text.toString()
                //add picture and name to the database

            }
        }

        //Add person to the database
        fun addPerson(p: String): String {

            return p
        }

    }

}
