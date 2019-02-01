package com.android.oblig;

import com.android.oblig.activities.Quiz;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class QuizTest {

    @Test
    public void getRandomPersonTest(){
        Quiz a = new Quiz();
        assertEquals(a.getRandomPerson(),null);
    }

    @Test
    public void scoreIncreasing(){
        Quiz a = new Quiz();
        String nameA = "ENDRE";
        String nameB = "ENDRE";
        String nameC = "DANIEL";
        a.correctAnswer("");
        assertEquals(a.score, 0);
        a.currentName = nameB;
        a.correctAnswer(nameA);
        assertEquals(a.score, 1);
        a.currentName = nameC;
        assertEquals(a.score, 1);
    }

}
