package com.android.oblig;

import com.android.oblig.activities.Quiz;
import com.android.oblig.modules.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class QuizTest {
    Quiz quiz;
    String nameA;
    String nameB;
    String nameC;

    @Before
    public void initialize() {
        nameA = "ENDRE";
        nameB = "ENDRE";
        nameC = "DANIEL";
        quiz = new Quiz();
    }

    @Test
    public void scoreIncreasing(){
        quiz.correctAnswer("");
        assertEquals(quiz.score, 0);
        quiz.currentName = nameB;
        quiz.correctAnswer(nameA);
        assertEquals(quiz.score, 1);
        quiz.currentName = nameC;
        assertEquals(quiz.score, 1);
    }



}
