package com.futsalrecord.futsalinfosystem;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.futsalrecord.futsalinfosystem.activities.login.FutsalLogin;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    @Rule
    public ActivityTestRule<FutsalLogin> testRuleFutsalLogin = new ActivityTestRule<>(FutsalLogin.class);

    @Test
    public void TestLoginFutsalUI() throws Exception {
        onView(withId(R.id.etFutsalLoginUsername)).perform(typeText("Gunjan Futsal"), closeSoftKeyboard());
        onView(withId(R.id.etFutsalLoginPassword)).perform(typeText("test<3"), closeSoftKeyboard());
        onView(withId(R.id.btnFutsalLogin)).perform(click());
    }

    @Test
    public void TestLoginUserUI() throws Exception {
        onView(withId(R.id.etUserLoginUsername)).perform(typeText("Gunjan Shakya"),closeSoftKeyboard());
        onView(withId(R.id.etUserLoginPassword)).perform(typeText("test<3"),closeSoftKeyboard());
        onView(withId(R.id.btnUserLogin)).perform(click());
    }
}
