package com.project.demorecord;

import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
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

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    //Find
    ViewInteraction addBtn = onView(withId(R.id.buttonAdded));
    ViewInteraction okBtn = onView(withId(android.R.id.button1));
    ViewInteraction editTextAge = onView(withId(R.id.editTextAge));
    ViewInteraction buttonGotoList = onView(withId(R.id.buttonGotoList));
    ViewInteraction editTextName = onView(withId(R.id.editTExtName));

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    //----- ไม่กรอก Name และ Age กดปุ่ม ADDED จะต้องเจอ Please Enter user info -----//
    @Test
    public void test1() {
        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        okBtn.perform(click());
    }

    //----- ไม่กรอก Name และ Age=20 กดปุ่ม ADDED จะต้องเจอ Please Enter user info -----//
    @Test
    public void test2() {
        SystemClock.sleep(1000);
        editTextAge.perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        okBtn.perform(click());
    }

    //----- ไม่มีการเพิ่ม UserInfo และกด GO TO LIST จะเจอ Not Found -----//
    @Test
    public void test3() {
        SystemClock.sleep(1000);
        buttonGotoList.perform(click());

        SystemClock.sleep(1000);
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }

    //----- ไม่กรอก Age และ Name=Ying กดปุ่ม ADDED จะต้องเจอ Please Enter user info -----//
    @Test
    public void test4() {
        SystemClock.sleep(1000);
        editTextName.perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));

        SystemClock.sleep(1000);
        okBtn.perform(click());
    }

    //----- กรอก Name=Ying และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ying อายุ 20 เป็นตัวแรก -----//
    @Test
    public void test5() {
        SystemClock.sleep(1000);
        editTextName.perform(replaceText("Ying"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        editTextAge.perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        buttonGotoList.perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(0, R.id.textName)).check(matches(withText("Ying")));
    }

    //----- กรอก Name=Ladarat และ Age=20 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Ladarat อายุ 20 ใน ListView ลำดับที่ 2 -----//
    @Test
    public void test6() {
        SystemClock.sleep(1000);
        editTextName.perform(replaceText("Ladarat"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        editTextAge.perform(replaceText("20"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        buttonGotoList.perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(1, R.id.textName)).check(matches(withText("Ladarat")));
    }

    //----- กรอก Name=Somkait และ Age=80 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Somkait อายุ 80 ใน ListView ลำดับที่ 3 -----//
    @Test
    public void test7() {
        SystemClock.sleep(1000);
        editTextName.perform(replaceText("Somkait"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        editTextAge.perform(replaceText("80"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        buttonGotoList.perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(2, R.id.textName)).check(matches(withText("Somkait")));
    }

    //----- กรอก Name=Prayoch และ Age=60 กดปุ่ม ADDED และกด GO TO LIST จะต้องเจอ Somkait อายุ 60 ใน ListView ลำดับที่ 4 -----//
    @Test
    public void test8() {
        SystemClock.sleep(1000);
        editTextName.perform(replaceText("Prayoch"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        editTextAge.perform(replaceText("60"), closeSoftKeyboard());

        SystemClock.sleep(1000);
        addBtn.perform(click());

        SystemClock.sleep(1000);
        buttonGotoList.perform(click());

        SystemClock.sleep(1000);
        onView(withRecyclerView(R.id.list).atPositionOnView(3, R.id.textName)).check(matches(withText("Prayoch")));
    }

}
