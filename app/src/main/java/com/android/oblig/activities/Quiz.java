package com.android.oblig.activities;

import android.arch.persistence.room.Room;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public int score = 0;
    public String currentName;
    private Person currentPerson;
    private int numberOfattempts = 0;
    private List<Person> list;
    private ImageView imageView;
    private TextView scoreBoard;
    public EditText nameGuess;
    private Button button;
    private boolean wrongAnswer = false;


    /**
     * Instantiates variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageView = (ImageView) findViewById(R.id.imageView);
        scoreBoard = (TextView) findViewById(R.id.score);

        list = MainMenu.db.personDao().getAll();

        scoreBoard.setText("Score: " + score + "/" + numberOfattempts);

        if(list != null){
            setPersonValues();
        }

    }

    /**
     *  Return a random person from list
     * @return Person
     */
    public Person getRandomPerson(){
        if(!(list == null)){
            Random random = new Random();
            int a = random.nextInt(list.size()) + 0;
            for(Person b : list){
                if(list.indexOf(b) == a) {
                    return b;
                }
            }
        }
        return null;
    }

    /**
     * Giving the view object values
     */
    public void setPersonValues(){
        currentPerson = getRandomPerson();
        currentName = currentPerson.getName();

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentPerson.getPicture(), 0, currentPerson.getPicture().length);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * clearing and updating existing values
     */
    public void clearAndSetValues(){
        numberOfattempts++;
        scoreBoard.setText("Score: " + score + "/" + numberOfattempts);
        nameGuess.setText("");

        if(list.size() > 0){
            setPersonValues();
        }else{
            button = (Button) findViewById(R.id.button);
            button.setEnabled(false);
            nameGuess.setEnabled(false);
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Checking if the user have entered correct answer
     * @param view
     */
    public void onNameEntered(View view){
        nameGuess = (EditText) findViewById(R.id.editText);
        String name = (String) nameGuess.getText().toString().toUpperCase();

        if(!wrongAnswer && correctAnswer(name)){
            clearAndSetValues();
        }else if(name.equals(currentName)){
            wrongAnswer = false;
            nameGuess.setTextColor(Color.BLACK);
            nameGuess.setEnabled(true);
            clearAndSetValues();
        }else {
            nameGuess.setText(currentName);
            nameGuess.setTextColor(Color.RED);
            nameGuess.setEnabled(false);
            wrongAnswer = true;
        }
    }

    public boolean correctAnswer(String guess){
        if((guess != null) && guess.equals(currentName)){
            score++;
            return true;
        }
        return false;
    }


}
