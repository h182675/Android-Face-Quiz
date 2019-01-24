package com.android.oblig.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.android.oblig.R;
import android.widget.EditText;
import android.widget.TextView;
import com.android.oblig.modules.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    private int score = 0;
    private String currentName;
    private Person currentPerson;
    private List<Person> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        TextView scoreBoard = (TextView) findViewById(R.id.score);
        scoreBoard.setText("Score: " + score);
        populateDB();
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

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.setImageResource(currentPerson.getPicture());
    }

    public void onNameEntered(View view){
        EditText nameGuess = (EditText) findViewById(R.id.editText);
        String name = (String) nameGuess.getText().toString();

        TextView scoreBoard = (TextView) findViewById(R.id.score);
        if(name.equals(currentName)){
            score+=1;
        }else{
            score-=1;
        }
        scoreBoard.setText("Score: " + score);
        nameGuess.setText("");
        setPersonValues();
    }


    public void populateDB(){
        //Person p1 = new Person(1,"DANIEL", R.drawable.picturedaniel);
        //Person p2 = new Person(2,"ENDRE", R.drawable.pictureendre);
        //list.add(p1);
        //list.add(p2);
    }


}
