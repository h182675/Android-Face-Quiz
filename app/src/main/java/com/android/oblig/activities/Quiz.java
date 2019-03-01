package com.android.oblig.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.*;
import com.android.oblig.R;
import com.android.oblig.modules.Person;
import com.android.oblig.modules.Preferences;
import java.util.List;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    public int score = 0;
    public String currentName;
    private Person currentPerson;
    private int numberOfAttempts = 0;
    public List<Person> list;
    private ImageView imageView;
    private TextView scoreBoard;
    private TextView highScore;
    public EditText nameGuess;
    private Button button;
    private boolean wrongAnswer = false;
    private Preferences preferences;
    private String scoreString;
    private String highScoreString;

    /**
     * Instantiates variables from view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        imageView = (ImageView) findViewById(R.id.imageView);
        scoreBoard = (TextView) findViewById(R.id.score);
        highScore = (TextView) findViewById(R.id.quiz_view_high_score);
        button = (Button) findViewById(R.id.button);
        nameGuess = (EditText) findViewById(R.id.editText);
        preferences = new Preferences(this);

        scoreString = getResources().getString(R.string.score);
        highScoreString = getResources().getString(R.string.high_score);

        list = MainMenu.db.personDao().getAll();
        scoreBoard.setText(scoreString + ": 0");
        int highScore = checkHighscore();
        this.highScore.setText(highScoreString + ": " + highScore);

        if(list != null && list.size() != 0){
            setPersonValues();
        }

    }

    /**
     * Giving the view object values
     */
    public void setPersonValues(){
        currentPerson = getRandomPerson();
        currentName = currentPerson.getName();

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentPerson.getPicture(), 0, currentPerson.getPicture().length);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * Checking if the user have entered correct answer
     * @param view
     */
    public void onNameEntered(View view){
        String name = (String) nameGuess.getText().toString().toUpperCase();

        if(!wrongAnswer && correctAnswer(name)){
            nameGuess.setTextColor(Color.GREEN);
            nameGuess.postOnAnimationDelayed(new Runnable() {
                @Override
                public void run() {
                    clearAndSetNewValues();
                }
            },1000);
        }else if(name.equals(currentName)){
            wrongAnswer = false;
            nameGuess.setEnabled(true);
            clearAndSetNewValues();
        }else {
            nameGuess.startAnimation(shakeError());
            nameGuess.setEnabled(false);
            nameGuess.postOnAnimationDelayed(new Runnable() {
                @Override
                public void run() {
                    nameGuess.setText(currentName);
                    wrongAnswer = true;
                    nameGuess.setTextColor(Color.RED);
                }
            },1000);
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
     * clearing and updating existing values
     */
    public void clearAndSetNewValues(){
        nameGuess.setTextColor(Color.BLACK);

        numberOfAttempts++;
        scoreBoard.setText(scoreString + ": " + score + "/" + numberOfAttempts);
        nameGuess.setText("");
        //Update high score
        int highscore = checkHighscore();
        highScore.setText(highScoreString + ": " + highscore);

        if(list.size() > 0){
            setPersonValues();
        }else{
            button.setEnabled(false);
            nameGuess.setEnabled(false);
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     *
     * @param guess
     * @return
     */
    public boolean correctAnswer(String guess){
        if((guess != null) && guess.equals(currentName)){
            score++;
            return true;
        }
        return false;
    }

    public Integer checkHighscore(){
        int highscore = preferences.getHighScore();
        if(score > highscore){
            highscore = score;
            preferences.setHighScore(score);
        }
        return highscore;
    }

    public TranslateAnimation shakeError(){
        TranslateAnimation shake = new TranslateAnimation(0,10,0,0);
        shake.setDuration(300);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
}
