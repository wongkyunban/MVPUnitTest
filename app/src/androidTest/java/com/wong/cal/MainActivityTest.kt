package com.wong.cal

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)// This annotation signifies that all the tests in this class are Android-specific tests.
internal class MainActivityTest {
    /**
     * we want to test an Activity,
     * we have to do a little setup.
     * We need to inform Espresso which Activity to open or launch before executing and destroy after executing any test method.
     *
     * Note that the @Rule annotation means that this is a JUnit4 test rule.
     * JUnit4 test rules are run before and after every test method (annotated with @Test).
     * In our own scenario, we want to launch MainActivity before every test method and destroy it after.
     * We also included the @JvmField Kotlin annotation.
     * This simply instructs the compiler not to generate getters and setters for the property and instead to expose it as a simple Java field.
     * Here are the three major steps in writing an Espresso test:
     * (1)Look for the widget (e.g. TextView or Button) you want to test.
     * (2)Perform one or more actions on that widget.
     * (3)Verify or check to see if that widget is now in a certain state.
     * */
    @Rule
    @JvmField
    val main = ActivityScenarioRule(MainActivity::class.java)


    @Test
    @Throws(Exception::class)
    fun clickLoginButton_opensLoginUi() {
        main.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.fabComputePrice))
    }
}