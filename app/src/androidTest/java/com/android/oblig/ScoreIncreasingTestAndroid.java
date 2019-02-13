package com.android.oblig;

import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import com.android.oblig.activities.MainMenu;
import com.android.oblig.activities.PersonList;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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

        onView(withId(R.id.editText2)).perform(typeText("DANIEL"), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        onView(withId(R.id.editText2)).perform(typeText("ENDRE"), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        SystemClock.sleep(750);

        onView(withIndex(withId(R.id.person_item_name),2)).check(matches(withText("ENDRE")));
        onView(withIndex(withId(R.id.person_item_deleteBtn),2)).perform(click());

        SystemClock.sleep(750);

        onView(withIndex(withId(R.id.person_item_name),1)).check(matches(withText("DANIEL")));
        onView(withIndex(withId(R.id.person_item_deleteBtn),1)).perform(click());

        SystemClock.sleep(750);

        onView(withIndex(withId(R.id.person_item_name),0)).check(matches(withText("PETTER")));


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

    /**
     * Custom matcher getting the index of items with unique id without changing the classes.
     * https://www.planetgeek.ch/2012/03/07/create-your-own-matcher/
     * @param matcher
     * @param index
     * @return View of the item at specified index
     */
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new BaseMatcher<View>() {

            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("at index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matches(Object item) {
                return matcher.matches(item) && currentIndex++ == index;
            }
        };
    }

}