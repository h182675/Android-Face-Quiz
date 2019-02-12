package com.android.oblig;

import android.os.SystemClock;
import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.android.oblig.activities.MainMenu;
import com.android.oblig.activities.PersonList;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScoreIncreasingTestAndroid {



    @Rule
    public ActivityTestRule<MainMenu> mActivityRule = new ActivityTestRule<>(MainMenu.class);


    @Test
    public void addPersonTest() {
        onView(withId(android.R.id.button1)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.menu_list)).atPosition(1).perform(click());
        onView(withId(R.id.add_person_btn)).perform(click());


        onView(withId(R.id.editText2)).perform(typeText("PETTER"), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        onView(allOf(withId(R.id.person_item_deleteBtn),
                withParentIndex(0)
        )).perform(click());

    }

    @Test
    public void quizScoreIncrease(){
        onData(anything()).inAdapterView(withId(R.id.menu_list)).atPosition(0).perform(click());

        onView(withId(R.id.score)).check(matches(withText(containsString("Score: 0"))));
        onView(withId(R.id.button)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.score)).check(matches(withText(containsString("Score: 0/1"))));

        onView(withId(R.id.editText)).perform(typeText("PETTER"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.score)).check(matches(withText(containsString("Score: 1/2"))));
    }

}