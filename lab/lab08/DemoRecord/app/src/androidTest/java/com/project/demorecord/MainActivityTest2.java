package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addUserInfoSuccess() {

        onView(withId(R.id.editTExtName)).perform(replaceText("jirapin54"), closeSoftKeyboard());   //put Name
        SystemClock.sleep(1000);

        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        SystemClock.sleep(1000);

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        onView(withText("jirapin54")).check(matches(isDisplayed()));

    }

    @Test
    public void test2(){

    }

}
