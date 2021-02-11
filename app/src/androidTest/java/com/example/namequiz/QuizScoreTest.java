package com.example.namequiz;

import android.util.Log;


import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.namequiz.activity.QuizActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;


@RunWith(AndroidJUnit4.class)
public class QuizScoreTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> activityRule =
            new ActivityScenarioRule<>(QuizActivity.class);


    @Test
    public void TestCorrectScore(){
        if(!QuizActivity.database.isEmpty()) {
            String name = QuizActivity.database.get(0).getName();

            onView(withId(R.id.guess))
                    .perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard());
            onView(withId(R.id.submit_Btn))
                    .perform(ViewActions.click());
            onView(withId(R.id.quizScore)).check(matches(withSubstring("Final score: 1/1")));
        }
        Log.d("Error", "Error test! Legg til en person for at testen skal bli godkjent.");
    }

    @Test
    public void TestWrongScore(){
        if(!QuizActivity.database.isEmpty()) {
        onView(withId(R.id.guess))
                .perform(ViewActions.typeText("test"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.submit_Btn))
                .perform(ViewActions.click());
        onView(withId(R.id.quizScore)).check(matches(withSubstring("Final score: 0/1")));
        }
        Log.d("Error", "Error test! Legg til en person for at testen skal bli godkjent.");
    }

}