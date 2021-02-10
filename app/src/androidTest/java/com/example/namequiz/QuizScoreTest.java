package com.example.namequiz;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.namequiz.activity.DatabaseActivity;
import com.example.namequiz.activity.MainActivity;
import com.example.namequiz.activity.QuizActivity;
import com.example.namequiz.database.PersonDAO;
import com.example.namequiz.database.PersonDatabase;
import com.example.namequiz.model.Person;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizScoreTest {

    private PersonDatabase db;
    private PersonDAO dao;

    @Before
    public void setupDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, PersonDatabase.class).build();
        dao = db.personDAO();


    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);
    QuizActivity quizActivity;

    @Test
    public void testScore(){
        Context context = ApplicationProvider.getApplicationContext();
        Resources res = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.jon);
        Person person1 = new Person("Jon", bitmap);
        Person person2 = new Person("Bendik", bitmap);
        Person person3 = new Person("Morten", bitmap);

        dao.addPerson(person1);
        dao.addPerson(person2);
        dao.addPerson(person3);
        List<Person> personList = db.personDAO().getAllPersons();
        int score = 0;

        String answer = "Jon";

        for(Person p : personList){
            if(p.getName().equals(answer)){
                score++;
            }
        }
        assert (score == 1);
    }

    @Test
    public void ScoreCountTest(){
        onView(withId(R.id.quizButton)).perform(ViewActions.click());
        quizActivity = (QuizActivity) getActivityInstance();
        onView(withId(R.id.guess))
                .perform(ViewActions.typeText("jon"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.submit_Btn))
                .perform(ViewActions.click());
        int score = quizActivity.getScore();

        assertEquals(1, score);
    }

    private Activity getActivityInstance() {
        final Activity[] currentActivity = {null};

        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection<Activity> resumedActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                Iterator<Activity> it = resumedActivity.iterator();
                currentActivity[0] = it.next();
            }
        });

        return currentActivity[0];
    }


}