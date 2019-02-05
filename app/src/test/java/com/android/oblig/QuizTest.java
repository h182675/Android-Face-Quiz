package com.android.oblig;

import com.android.oblig.activities.Quiz;
import com.android.oblig.modules.Person;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class QuizTest {
    Quiz a;
    String nameA;
    String nameB;
    String nameC;

    @Before
    public void initialize() {
        nameA = "ENDRE";
        nameB = "ENDRE";
        nameC = "DANIEL";
        a = new Quiz();
    }

    @Test
    public void scoreIncreasing(){
        a.correctAnswer("");
        assertEquals(a.score, 0);
        a.currentName = nameB;
        a.correctAnswer(nameA);
        assertEquals(a.score, 1);
        a.currentName = nameC;
        assertEquals(a.score, 1);
    }

}
