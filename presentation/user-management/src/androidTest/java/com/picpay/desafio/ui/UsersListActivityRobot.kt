package com.picpay.desafio.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.picpay.desafio.ui.matcher.matchChildPosition

fun UsersListActivityTest.launch(
    invoke: UsersListActivityRobot.() -> Unit
): UsersListActivityRobot {
    launchActivity<UsersListActivity>()
    return UsersListActivityRobot().apply(invoke)
}

class UsersListActivityRobot {

    fun checkTitleTextIsDisplayed(titleResId: Int) {
        onView(withText(titleResId)).check(matches(isDisplayed()))
    }

    fun checkUserNameAtPosition(textToMatch: String, position: Int) {
        onView(withId(R.id.recyclerView)).check(matches(
            matchChildPosition(R.id.username, position, withText(textToMatch))
        ))
    }

    fun checkErrorScreenIsVisible() {
        onView(withId(R.id.error_content)).check(matches(isDisplayed()))
    }
}
