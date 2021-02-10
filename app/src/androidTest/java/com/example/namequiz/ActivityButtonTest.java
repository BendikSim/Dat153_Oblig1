package com.example.namequiz;

import android.content.ComponentName;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.namequiz.activity.DatabaseActivity;
import com.example.namequiz.activity.MainActivity;
import com.example.namequiz.activity.QuizActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents.*;
import androidx.test.espresso.intent.matcher.IntentMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityButtonTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);



    @Test
    public void launchActivityTest(){
         ActivityScenario<MainActivity> scenario = activityRule.getScenario();
         Intents.init();
         onView(withId(R.id.databaseButton))
                .perform(ViewActions.click());
        intended(hasComponent(DatabaseActivity.class.getName()));
    }
}
