package com.android.oblig.activities;

import android.arch.persistence.room.Room;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.android.oblig.R;
import android.widget.EditText;
import android.widget.TextView;
import com.android.oblig.modules.AppDatabase;
import com.android.oblig.modules.Person;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    private int score = 0;
    private String currentName;
    private Person currentPerson;
    private List<Person> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        TextView scoreBoard = (TextView) findViewById(R.id.score);
        scoreBoard.setText("Score: " + score);




        //populateDB();

        list = MainMenu.db.personDao().getAll();

        if(list.size() > 0);
            setPersonValues();

    }

    private Person getRandomPerson(){
        Random random = new Random();
        int a = random.nextInt(list.size()) + 1;
        for(Person b : list){
            if(b.getId() == a) {
                return b;
            }
        }
        return list.get(2);
    }

    private void setPersonValues(){
        currentPerson = getRandomPerson();
        currentName = currentPerson.getName();


        Bitmap bitmap = BitmapFactory.decodeByteArray(currentPerson.getPicture(), 0, currentPerson.getPicture().length);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    public void onNameEntered(View view){

        EditText nameGuess = (EditText) findViewById(R.id.editText);
        String name = (String) nameGuess.getText().toString();

        TextView scoreBoard = (TextView) findViewById(R.id.score);
        if(name.equals(currentName)){
            score+=1;
        }
        scoreBoard.setText("Score: " + score + "/" + list.size());
        nameGuess.setText("");
        setPersonValues();


    }

/**
    public void populateDB(){

        Drawable d = getDrawable(R.drawable.picturedaniel); // the drawable (Captain Obvious, to the rescue!!!)
        Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();

        Person p1 = new Person(1,"DANIEL", bitmapdata);

        Drawable c = getDrawable(R.drawable.pictureendre); // the drawable (Captain Obvious, to the rescue!!!)
        bitmap = ((BitmapDrawable)c).getBitmap();
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] e = stream.toByteArray();

        Person p2 = new Person(2,"ENDRE", e);

        db.personDao().insert(p1);
        db.personDao().insert(p2);

        //list.add(p1);
        //list.add(p2);
    }
 **/
}
