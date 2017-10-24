package com.project.demorecord;


import android.content.ClipData;
import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test1() {

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());

    }

    @Test
    public void test2() {

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());

    }

    @Test
    public void test3() {

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Not Found")).check(matches(isDisplayed()));

    }

    @Test
    public void test4() {

        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());

    }

    @Test
    public void test5() {

        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

//        onData(hasToString(startsWith("Ying")))
//                .inAdapterView(withId(R.id.list))
//                .atPosition(0)
//                .inAdapterView(withId(R.id.textName))
//                .check(matches(isDisplayed()));


//        onData(instanceOf(MainActivity.class))
//                .inAdapterView(withId(R.id.list))
//                .atPosition(0)
//                .check(matches(hasDescendant(withText("Ying"))));


//        onView(withId(R.id.list))
//                .perform(
//                        RecyclerViewActions.actionOnItemAtPosition(0, click())
//                );
    }


    @Test
    public void test6() {

        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

    }
    @Test
    public void test7() {

        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

    }

    @Test
    public void test8() {

        SystemClock.sleep(1000);
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());

        SystemClock.sleep(1000);
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());

    }


}
