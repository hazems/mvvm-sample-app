package com.example.test.mvvmsampleapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.test.mvvmsampleapp.view.ui.MainActivity
import com.example.test.mvvmsampleapp.view.viewholders.ProjectViewHolder
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith





/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class UiTests {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    /**
     * Lazy example test, phone should be connected to the internet.
     * Ideally you need to implement a mock web server.
     */
    @Before
    fun waitForLoading() {
        onView(withId(R.id.loading_projects)).check(matches(isDisplayed()))
        Thread.sleep(1000)
    }

    @Test
    fun clickFirstElement() {
        onView(withId(R.id.project_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ProjectViewHolder>(0, click()))

        onView(withId(R.id.imageView)).check(matches(isDisplayed()))
    }

}