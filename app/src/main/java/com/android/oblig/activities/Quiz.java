package com.android.oblig.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.android.oblig.R;
import android.widget.EditText;
import android.widget.TextView;

public class Quiz extends AppCompatActivity {

    private int score = 0;
    private String[] personList = {"IDA", "INGRID", "HELENE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView scoreBoard = (TextView) findViewById(R.id.score);
        scoreBoard.setText("Score: " + score);

    }

    public void onNameEntered(View view){
        EditText nameGuess = (EditText) findViewById(R.id.editText);
        String name = (String) nameGuess.getText().toString();

        TextView scoreBoard = (TextView) findViewById(R.id.score);
        if(name.equals(personList[0])){
            score+=1;
        }else{
            score-=1;
        }
        scoreBoard.setText("Score: " + score);
    }
}
