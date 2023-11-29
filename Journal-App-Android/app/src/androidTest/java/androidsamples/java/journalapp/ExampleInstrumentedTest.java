package androidsamples.java.journalapp;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.espresso.contrib.PickerActions;
import org.hamcrest.Matchers;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.hamcrest.Matchers;
import android.widget.DatePicker;

//import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import android.app.Activity;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Collection;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import android.view.View;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleInstrumentedTest {
    private View v;
    @Rule
    public ActivityScenarioRule<MainActivity> MainActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addButtonAcc(){
        onView(withId(R.id.btn_add_entry)).check(matches(isClickable())).perform(click());
    }

    @Test
    public void infoButtonAcc(){
        onView(withId(R.id.menu_info)).check(matches(isClickable())).perform(click());
    }

    @Test
    public void test1()
    {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title)).perform(typeText("Ttitle"), closeSoftKeyboard());
        onView(withId(R.id.btn_entry_date)).perform(click());
    }
    @Test
    public void test2()
    {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title)).perform(typeText("Today"), closeSoftKeyboard());
        onView(withId(R.id.btn_start_time)).perform(click());
    }
    @Test
    public void test3()
    {
        onView(withId(R.id.btn_add_entry)).perform(click());
        onView(withId(R.id.edit_title)).perform(typeText("Note"), closeSoftKeyboard());
        onView(withId(R.id.btn_save)).perform(click());
    }

}